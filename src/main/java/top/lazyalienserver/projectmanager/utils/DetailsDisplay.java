package top.lazyalienserver.projectmanager.utils;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import top.lazyalienserver.projectmanager.command.ProjectManager;
import top.lazyalienserver.projectmanager.define.Project;

public class DetailsDisplay {
    public DetailsDisplay(String projectName, ServerCommandSource source) throws CommandSyntaxException {
        boolean findFlag=false;
        for (Project project : ProjectManager.projects){
            if(project.ProjectName.equals(projectName)){
                String message = "项目名称:"+projectName+"\n"+
                        "项目地点:"+project.ProjectPlacePOS[0]+","+project.ProjectPlacePOS[1]+","+project.ProjectPlacePOS[2]+"\n"+
                        "项目人员:"+project.projectPersonnel+"\n"+
                        "项目负责人:"+project.projectLeader+"\n"+
                        "项目状态:"+(project.Status==0?"未审批 未完工":project.Status==1?"已审批 已完工":project.Status==2?"已审批 已完工":project.Status==-1?"已取消":project.Status==-2?"未审批 已完工(疑惑)":null)+"\n"+
                        "项目文件:"+project.LitematicaFilesName+"\n"+
                        "项目工期:"+project.duration+"\n"+
                        "项目材料区域:"+project.materialAreaCoordinates[0]+","+project.materialAreaCoordinates[1]+","+project.materialAreaCoordinates[2]+"\n"+
                        "前置项目:"+project.PreProject+"\n"+
                        "子项目:"+project.SubProject+"\n"+
                        "备注:"+project.Note+"Note功能暂未完成";
                source.getPlayer().sendMessage(new LiteralText(message),false);
                findFlag=true;
            }
        }
        if(!findFlag){
            source.sendError(new LiteralText("Project not found"));
        }
    }
}
