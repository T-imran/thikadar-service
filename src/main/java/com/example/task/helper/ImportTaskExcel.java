package com.example.task.helper;

import com.example.task.model.Task;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportTaskExcel {
   static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static Boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        return (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
    }

    //convert excel to list of task
    public static List<Task> convertExcelToListOfTask(InputStream inputStream){
        List<Task> list = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("task_data");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()){
                Row row = iterator.next();
                if(rowNumber==0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell>cells  = row.iterator();

                int cid = 0;
                Task task = new Task();

                while(cells.hasNext()){
                    Cell cell = cells.next();

                    switch (cid){
                        case 0:
                            task.setTaskTitle(cell.getStringCellValue());
                            break;
                        case 1:
                            task.setTaskDesc(cell.getStringCellValue());
                            break;
                        case 2:
                            LocalDate taskStartDate = LocalDate.parse(cell.getStringCellValue(), formatter);
                            task.setTaskStartDate(taskStartDate);
                            break;
                        case 3:
                            LocalDate taskEndDate = LocalDate.parse(cell.getStringCellValue(), formatter);
                            task.setTaskEndDate(taskEndDate);
                            break;
                        case 4:
                            task.setTotalHour(cell.getStringCellValue());
                            break;
                        case 5:
                            task.setProgress((int)cell.getNumericCellValue());
                            break;
                        case 6:
                            task.setStatus(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(task);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
