package com.pointer.controller;

import com.pointer.BlankBlockApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Path;

@RestController
public class SolveController {

    @RequestMapping(value = "/solve", method = RequestMethod.GET)
    public String runSolve(@RequestParam("situation") String situation){
        System.out.println("Get a request: " + situation);


        String str = "ERROR";
        Runtime runtime = Runtime.getRuntime();
        Process process = null;

        // Windows
//        String exePathString = Thread.currentThread().getContextClassLoader().getResource("solveExe").getPath();

//        ClassPathResource resource = new ClassPathResource("solveExe");
//        String exePathString = resource.getPath();

//        String exePathString = BlankBlockApplication.class.getResource("\\solveExe").getPath();

//        Resource resource = new ClassPathResource("static/syslog/cert/server.crt");

//        String exePathString = Resources.getResource("solveExe").getPath();

        // Linux
        String exePathString = "/home/ubuntu/BlankBlock/solveExe";

//        if(exePathString.contains("file:")){
//            exePathString = exePathString.substring(5, exePathString.length());
//            exePathString = exePathString.replace("!", "");
//        }
//        System.out.println("路径: " + exePathString);
        String cmd = exePathString + " " + String.valueOf(situation.length()) + " " +situation;
        try {
            process = runtime.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                if (!((str = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
//        Reader reader = new InputStreamReader(inputStream);
//        BufferedReader bReader = new BufferedReader(reader);
//        String line = null;
//        while (true) {
//            try {
//                if (!((line = bReader.readLine()) != null)) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println(line);
//        }
        System.out.println("Result is:" + str);
        return str;
    }
}
