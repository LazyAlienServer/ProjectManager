package top.lazyalienserver.projectmanager.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import top.lazyalienserver.projectmanager.command.ProjectManager;
import top.lazyalienserver.projectmanager.define.Project;

import java.io.*;
import java.util.List;

public class ProjectListSave {
    private static final Gson gson = new Gson();
    private static File file;
    public static void pathSet(String path){
        file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // 创建所有不存在的父目录
        }
    }
    public static void saveProjectList() throws IOException {
        // 保存项目列表
        String FileContent = gson.toJson(ProjectManager.projects);
        if(!file.exists()) file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(FileContent);
        fileWriter.flush();
        fileWriter.close();
    }
    public static List<Project> readProjectList() throws IOException {
        // 读取项目列表
        if(!file.exists()){
            file.createNewFile();
            return null;
        }else{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            String FileContent = stringBuilder.toString();
            return gson.fromJson(FileContent, new TypeToken<List<Project>>(){}.getType());
        }

    }
}
