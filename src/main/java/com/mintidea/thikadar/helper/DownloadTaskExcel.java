//package com.mintidea.thikadar.helper;
//
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class DownloadTaskExcel {
//
//    public static String[] HEADERS={
//            "id",
//            "taskTitle",
//            "taskDesc",
//            "taskStartDate",
//            "taskEndDate",
//            "totalHour",
//            "progress",
//            "status"
//    };
//
//    public static String SHEET_NAME="task_data";
//
//    public static ByteArrayInputStream dataToExcel(List<Task> list) throws IOException{
//        //crate work book
//        Workbook workbook = new XSSFWorkbook();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//            //create sheet
//            Sheet sheet = workbook.createSheet(SHEET_NAME);
//
//            //create row
//            Row row = sheet.createRow(0);
//            //create header
//            for(int i=0; i <HEADERS.length; i++){
//                Cell cell = row.createCell(i);
//               cell.setCellValue(HEADERS[i]);
//            }
//
//            //value row
//
//            int rowIndex=1;
//            for(Task t:list){
//                Row dataRow = sheet.createRow(rowIndex);
//                rowIndex++;
//                dataRow.createCell(0).setCellValue(t.getId());
//                dataRow.createCell(1).setCellValue(t.getTaskTitle());
//                dataRow.createCell(2).setCellValue(t.getTaskDesc());
//                dataRow.createCell(3).setCellValue(t.getTaskStartDate().toString());
//                dataRow.createCell(4).setCellValue(t.getTaskEndDate().toString());
//                dataRow.createCell(5).setCellValue(t.getTotalHour());
//                dataRow.createCell(6).setCellValue(t.getProgress());
//                dataRow.createCell(7).setCellValue(t.getStatus());
//            }
//            workbook.write(out);
//
//            return new ByteArrayInputStream(out.toByteArray());
//
//        }catch (IOException e){
//            e.printStackTrace();
//            System.out.println("fail to download excel");
//            return null;
//        }finally {
//            workbook.close();
//            out.close();
//        }
//    }
//}
