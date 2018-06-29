package com.lambo.code.utils;


import com.lambo.common.utils.lang.StringUtils;
import com.lambo.common.utils.other.JdbcUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代码生成类
 * Created by lambo on 2017/1/10.
 */
public class MybatisGeneratorUtil {

	/**
	 * generatorConfig模板路径
	 */
	private static String generatorConfig_vm = "/template/generatorConfig.vm";
	/**
	 * Service模板路径
	 */
	private static String service_vm = "/template/Service.vm";
	/**
	 * ServiceMock模板路径
	 */
	private static String serviceMock_vm = "/template/ServiceMock.vm";
	/**
	 * ServiceImpl模板路径
	 */
	private static String serviceImpl_vm = "/template/ServiceImpl.vm";
	/**
	 * Controller模板路径
	 */
	private static String controller_vm = "/template/Controller.vm";
	/**
	 * Result模板路径
	 */
	private static String result_vm = "/template/Result.vm";
	/**
	 * ResultConstant模板路径
	 */
	private static String resultConstant_vm = "/template/ResultConstant.vm";

	/**
	 * queryVue模板路径
	 */
	private static String queryVue_vm = "/template/queryVue.vm";
	/**
	 * editVue模板路径
	 */
	private static String editVue_vm = "/template/editVue.vm";

	/**
	 * 根据模板生成generatorConfig.xml文件
	 * @param jdbc_driver   驱动路径
	 * @param jdbc_url      链接
	 * @param jdbc_username 帐号
	 * @param jdbc_password 密码
	 * @param database      数据库
	 * @param table_name  表名
	 * @param package_name  包名
	 */
	public static void generator(
			String jdbc_driver,
			String jdbc_url,
			String jdbc_username,
			String jdbc_password,
			String database,
			String table_name,
			String table_key,
			boolean is_auto_inc,
			String package_name) throws Exception{

		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
			service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
			serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath().replaceFirst("/", "");
			serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/", "");
			controller_vm = MybatisGeneratorUtil.class.getResource(controller_vm).getPath().replaceFirst("/", "");
			result_vm = MybatisGeneratorUtil.class.getResource(result_vm).getPath().replaceFirst("/", "");
			resultConstant_vm = MybatisGeneratorUtil.class.getResource(resultConstant_vm).getPath().replaceFirst("/", "");

			queryVue_vm = MybatisGeneratorUtil.class.getResource(queryVue_vm).getPath().replaceFirst("/", "");
			editVue_vm = MybatisGeneratorUtil.class.getResource(editVue_vm).getPath().replaceFirst("/", "");
		} else {
			generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
			service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath();
			serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath();
			serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath();
			controller_vm = MybatisGeneratorUtil.class.getResource(controller_vm).getPath();
			result_vm = MybatisGeneratorUtil.class.getResource(result_vm).getPath();
			resultConstant_vm = MybatisGeneratorUtil.class.getResource(resultConstant_vm).getPath();

			queryVue_vm = MybatisGeneratorUtil.class.getResource(queryVue_vm).getPath();
			editVue_vm = MybatisGeneratorUtil.class.getResource(editVue_vm).getPath();
		}

		String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
		String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replaceFirst("/", "");
		String generatorConfig_xml = (MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/resources/generatorConfig.xml").replaceFirst("/", "");
		String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + database + "' AND TABLE_NAME = '" + table_name + "';";

		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		List<Map<String, Object>> tables = new ArrayList<>();
		try {
			VelocityContext context = new VelocityContext();
			Map<String, Object> table;

			// 查询指定表元数据
			JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
			List<Map> result = jdbcUtil.selectByParams(sql, null);
			for (Map map : result) {
				table = new HashMap<>();
				Object tableName = map.get("TABLE_NAME");
				String moduleName = StringUtils.lineToHump(ObjectUtils.toString(tableName));
				table.put("table_name", tableName);
				table.put("model_name", moduleName);
				if(is_auto_inc){
					table.put("table_key",table_key);
				}
				String columnSql = "SELECT COLUMN_NAME,DATA_TYPE,IS_NULLABLE,COLUMN_DEFAULT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + database + "' AND TABLE_NAME='" + map.get("TABLE_NAME") + "'";
				List<Map> columnResult = jdbcUtil.selectByParams(columnSql, null);
				List<Map> columns = new ArrayList<Map>();
				for (Map column : columnResult) {
					String lineColumnName = column.get("COLUMN_NAME").toString().toLowerCase();
					String humpColumnName = StringUtils.toLowerCaseFirstOne(StringUtils.lineToHump(lineColumnName));
					String allHumpColumnName = StringUtils.lineToHump(lineColumnName);
					column.put("COLUMN_NAME_HUMP",humpColumnName);
					column.put("COLUMN_NAME_HUMP_ALL",allHumpColumnName);
					columns.add(column);
				}
				table.put("columns",columns);
				tables.add(table);
				// 删除旧代码
				deleteDir(new File(basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/model/" + moduleName + ".java"));
				deleteDir(new File(basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/model/" + moduleName + "Example.java"));
				deleteDir(new File(basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper/" + moduleName + "Mapper.java"));
				deleteDir(new File(basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper/" + moduleName + "Mapper.xml"));
			}
			jdbcUtil.release();

			context.put("tables", tables);
			context.put("generator_javaModelGenerator_targetPackage", package_name + ".dao.model");
			context.put("generator_sqlMapGenerator_targetPackage", package_name + ".dao.mapper");
			context.put("generator_javaClientGenerator_targetPackage", package_name + ".dao.mapper");
			context.put("targetProject", basePath);

			context.put("generator_jdbc_driver", jdbc_driver);
			context.put("generator_jdbc_url", jdbc_url);
			context.put("generator_jdbc_username", jdbc_username);
			context.put("generator_jdbc_password", jdbc_password);
			VelocityUtil.generate(generatorConfig_vm, generatorConfig_xml, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

		System.out.println("========== 开始运行MybatisGenerator ==========");
		List<String> warnings = new ArrayList<>();
		File configFile = new File(generatorConfig_xml);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println(warning);
		}
		System.out.println("========== 结束运行MybatisGenerator ==========");

		/*System.out.println("========== 开始生成Constant ==========");
		String resultPatch = basePath  + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/constant";

		String result = resultPatch + "/" + module + "Result.java";
		String resultConstant = resultPatch + "/" + module + "ResultConstant.java";
		// 生成result
		File resultFile = new File(result);
		if (!resultFile.exists()) {
			VelocityContext context = new VelocityContext();
			context.put("package_name", package_name);
			context.put("module", module);
			context.put("moduleV", StringUtils.toLowerCaseFirstOne(module));
			context.put("ctime", ctime);
			VelocityUtil.generate(result_vm, result, context);
			System.out.println(result);
		}

		// 生成resultConstant
		File resultConstantFile = new File(resultConstant);
		if (!resultConstantFile.exists()) {
			VelocityContext context = new VelocityContext();
			context.put("package_name", package_name);
			context.put("module", module);
			context.put("ctime", ctime);
			VelocityUtil.generate(resultConstant_vm, resultConstant, context);
			System.out.println(resultConstant);
		}
		System.out.println("========== 结束生成Constant ==========");*/

		System.out.println("========== 开始生成Service,Controller,Vue ==========");
		String servicePath = basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/service/api";
		String serviceImplPath = basePath  + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/service/impl";
		String controllerPath = basePath  + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/controller";
		String vuePath = basePath  + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/vue";

		for (int i = 0; i < tables.size(); i++) {
			String model = StringUtils.lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")));
			String service = servicePath + "/" + model + "Service.java";
			//String serviceMock = servicePath + "/" + model + "ServiceMock.java";
			String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
			String controller = controllerPath + "/" + model + "Controller.java";
			String queryVue = vuePath + "/" + model + "Query.vue";
			String editVue = vuePath + "/" + model + "Edit.vue";

			// 生成service
			File serviceFile = new File(service);
			if (!serviceFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(service_vm, service, context);
				System.out.println(service);
			}
			// 生成serviceMock
			/*File serviceMockFile = new File(serviceMock);
			if (!serviceMockFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceMock_vm, serviceMock, context);
				System.out.println(serviceMock);
			}*/
			// 生成serviceImpl
			File serviceImplFile = new File(serviceImpl);
			if (!serviceImplFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("mapper", StringUtils.toLowerCaseFirstOne(model));
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
				System.out.println(serviceImpl);
			}
			// 生成controller
			File controllerFile = new File(controller);
			if (!controllerFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("modelV", StringUtils.toLowerCaseFirstOne(model));
				//context.put("module", module);
				context.put("search",StringUtils.lineToHump(table_key));
				context.put("pk",StringUtils.toLowerCaseFirstOne(StringUtils.lineToHump(table_key)));
				context.put("is_auto_inc",is_auto_inc);
				context.put("columns",tables.get(i).get("columns"));
				context.put("ctime", ctime);
				VelocityUtil.generate(controller_vm, controller, context);
				System.out.println(controller);
			}


			// 生成queryVue
			File queryVueFile = new File(queryVue);
			if (!queryVueFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("modelV", StringUtils.toLowerCaseFirstOne(model));
				context.put("pk",StringUtils.toLowerCaseFirstOne(StringUtils.lineToHump(table_key)));
				context.put("is_auto_inc",is_auto_inc);
				context.put("columns",tables.get(i).get("columns"));
				VelocityUtil.generate(queryVue_vm, queryVue, context);
				System.out.println(queryVue);
			}

			// 生成editVue
			File editFile = new File(editVue);
			if (!editFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("title", table_name);
				context.put("modelV", StringUtils.toLowerCaseFirstOne(model));
				context.put("pk",StringUtils.toLowerCaseFirstOne(StringUtils.lineToHump(table_key)));
				context.put("is_auto_inc",is_auto_inc);
				context.put("columns",tables.get(i).get("columns"));
				VelocityUtil.generate(editVue_vm, editVue, context);
				System.out.println(editVue);
			}

			String modelV = StringUtils.toLowerCaseFirstOne(model);
			String str = "import "+modelV+"Query from '@/components/"+modelV+"/"+model+"Query';\n";
			str += "import "+modelV+"Edit from '@/components/"+modelV+"/"+model+"Edit';\n\n";
			str += "{ \n";
			str += "	path: 'manage/"+modelV+"/query',\n";
			str += "	meta:{\n";
			str += "		title: '"+modelV+"Query',\n";
			str += "	},\n";
			str += "	name:'"+modelV+"Query',\n";
			str += "	component: "+modelV+"Query\n";
			str += "},\n";
			str += "{\n";
			str += "	path: 'manage/"+modelV+"/edit',\n";
			str += "	meta:{\n";
			str += "		title: '"+modelV+"Edit',\n";
			str += "	},\n";
			str += "	name:'"+modelV+"Edit',\n";
			str += "	component: "+modelV+"Edit\n";
			str += "}";

			System.out.println("拷贝vue到自己前台目录，配置vue的router,其中注意路由的name属性，参考如下：\n"+str);
		}
		System.out.println("========== 结束生成Service,Controller,Vue ==========");
	}

	// 递归删除非空文件夹
	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteDir(files[i]);
			}
		}
		dir.delete();
	}

}
