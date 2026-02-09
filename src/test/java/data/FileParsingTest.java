package data;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParsingTest {

    @Test
    void pdfTest() throws Exception {
        open("https://docs.junit.org/5.5.0/user-guide/index.html");
        File downloadedPdf = $("a[href='index.pdf']").download();
        PDF content = new PDF(downloadedPdf);
        assertThat(content.author).contains("Sam Brannen");
    }


    @Test
    void xlsTest() throws Exception {
        open("https://samplelib.com/ru/sample-xls.html");
        ElementsCollection selenideElements = $$x("//a[text()='Скачать']");
        File download = selenideElements.get(1).download();
        try (InputStream is = new FileInputStream(download)) {
            XLS xls = new XLS(is);
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).contains("test1");

        }


    }


    @Test
    void csvTest() throws Exception {
        ClassLoader cl = FileParsingTest.class.getClassLoader();

        try (InputStream resources = cl.getResourceAsStream("ttt.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(resources))) {
            List<String[]> content = reader.readAll();
            assertThat(content.getFirst()).contains("123");
        }
    }


    @Test
    void jsonParseTest() throws Exception {
        Gson gson = new Gson();
        ClassLoader cl = FileParsingTest.class.getClassLoader();
        try (InputStream resources = cl.getResourceAsStream("glossary.json");
             InputStreamReader reader = new InputStreamReader(resources)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("title").getAsString()).isEqualTo("example glossary");

        }

    }
}
