///**
// * This file is part of the jcrontab package
// * Copyright (C) 2001-2003 Israel Olalla
// * <p>
// * This library is free software; you can redistribute it and/or
// * modify it under the terms of the GNU Lesser General Public
// * License as published by the Free Software Foundation; either
// * version 2 of the License, or (at your option) any later version.
// * <p>
// * This library is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// * Lesser General Public License for more details.
// * <p>
// * You should have received a copy of the GNU Lesser General Public
// * License along with this library; if not, write to the Free
// * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston,
// * MA 02111-1307, USA
// * <p>
// * For questions, suggestions:
// * <p>
// * iolalla@yahoo.com
// */
//
//package com.lambo.schedule.tests;
//
//import java.util.Date;
//
///**
// * This class helps the testing process to make easier testing
// * The objective of this test is to test Tunnable passing parameters
// * to the constructor and printing those parameters
// * @author $Author: caoxm $
// * @version $Revision: 2867 $
// */
//public class TaskTest4 implements Runnable {
//
//    private static String[] args;
//
//    public TaskTest4(String[] args) {
//
//        Date now = new Date();
//
//        System.out.print("TaskTest4 Running           " + now);
//
//        //System.out.print("Hola mundo from TaskTest4 \n");
//        //this.args = args;
//    }
//
//    @Override
//    public void run() {
//
//
//        if (args != null && args.length > 0) {
//            System.out.print("Those Are the args you passed: \n");
//            for (int i = 0; i < args.length; i++) {
//                System.out.print("This is arg " +
//                        i + " " + args[i] + "\n");
//            }
//            System.out.print("Hola mundo from TaskTest4.run\n");
//        }
//    }
//}
