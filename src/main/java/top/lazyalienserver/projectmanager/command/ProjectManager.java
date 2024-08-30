package top.lazyalienserver.projectmanager.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import top.lazyalienserver.projectmanager.define.Project;
import top.lazyalienserver.projectmanager.utils.DetailsDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectManager {
    // 存储所有项目的列表
    public static List<Project> projects = new ArrayList<>();

    // 注册命令
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("ProjectManager")
                        .then(CommandManager.literal("listProjects")
                                .executes(context -> {
                                    StringBuilder text= new StringBuilder(new String("Project List:\n"));
                                    for (Project project : projects) {
                                        text.append(project.ProjectName).append("\n");
                                    }
                                    context.getSource().getPlayer().sendMessage(new LiteralText(text.toString()), false);
                                    return 1;
                                }))
                        .then(CommandManager.literal("createProject")
                                .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                        .executes(context -> {
                                            try{
                                                String projectName = StringArgumentType.getString(context, "ProjectName");
                                                Project project = new Project();
                                                project.ProjectName = projectName;
                                                projects.add(project);
                                                context.getSource().getPlayer().sendMessage(new LiteralText("Project created"), false);
                                                return 1;
                                            }catch (Exception e){
                                                context.getSource().sendError(new LiteralText(e.toString()));
                                                return 0;
                                            }
                                        })))
                        .then(CommandManager.literal("setProjectPlacePOS")
                                .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                        .then(CommandManager.argument("x", StringArgumentType.string())
                                                .then(CommandManager.argument("y", StringArgumentType.string())
                                                        .then(CommandManager.argument("z", StringArgumentType.string())
                                                                .executes(context -> {
                                                                    String projectName = StringArgumentType.getString(context, "ProjectName");
                                                                    long x = Long.parseLong(StringArgumentType.getString(context, "x"));
                                                                    long y = Long.parseLong(StringArgumentType.getString(context, "y"));
                                                                    long z = Long.parseLong(StringArgumentType.getString(context, "z"));
                                                                    Optional<Project> projectOpt = projects.stream()
                                                                            .filter(p -> p.ProjectName.equals(projectName))
                                                                            .findFirst();
                                                                    if (projectOpt.isPresent()) {
                                                                        Project project = projectOpt.get();
                                                                        project.ProjectPlacePOS[0] = x;
                                                                        project.ProjectPlacePOS[1] = y;
                                                                        project.ProjectPlacePOS[2] = z;
                                                                        context.getSource().getPlayer().sendMessage(new LiteralText("Project Place POS set"), false);
                                                                        return 1;
                                                                    } else {
                                                                        context.getSource().sendError(new LiteralText("Project not found"));
                                                                        return 0;
                                                                    }
                                                                }))))))
                .then(CommandManager.literal("setProjectPersonnel")
                        .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                .then(CommandManager.argument("projectPersonnel", StringArgumentType.string())
                                        .executes(context -> {
                                            String projectName = StringArgumentType.getString(context, "ProjectName");
                                            List<String> projectPersonnel = List.of(StringArgumentType.getString(context, "projectPersonnel").split(","));
                                            Optional<Project> projectOpt = projects.stream()
                                                    .filter(p -> p.ProjectName.equals(projectName))
                                                    .findFirst();
                                            if (projectOpt.isPresent()) {
                                                Project project = projectOpt.get();
                                                project.projectPersonnel = projectPersonnel;
                                                context.getSource().getPlayer().sendMessage(new LiteralText("Project Personnel set"), false);
                                                return 1;
                                            } else {
                                                context.getSource().sendError(new LiteralText("Project not found"));
                                                return 0;
                                            }
                                        }))))
                .then(CommandManager.literal("setProjectLeader")
                        .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                .then(CommandManager.argument("projectLeader", StringArgumentType.string())
                                        .executes(context -> {
                                            String projectName = StringArgumentType.getString(context, "ProjectName");
                                            List<String> projectLeader = List.of(StringArgumentType.getString(context, "projectLeader").split(","));
                                            Optional<Project> projectOpt = projects.stream()
                                                    .filter(p -> p.ProjectName.equals(projectName))
                                                    .findFirst();
                                            if (projectOpt.isPresent()) {
                                                Project project = projectOpt.get();
                                                project.projectLeader = projectLeader;
                                                context.getSource().getPlayer().sendMessage(new LiteralText("Project Leader set"), false);
                                                return 1;
                                            } else {
                                                context.getSource().sendError(new LiteralText("Project not found"));
                                                return 0;
                                            }
                                        }))))
                .then(CommandManager.literal("addLitematicaFilesName")
                        .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                .then(CommandManager.argument("LitematicaFilesName", StringArgumentType.string())
                                        .executes(context -> {
                                            String projectName = StringArgumentType.getString(context, "ProjectName");
                                            List<String> litematicaFilesName = List.of(StringArgumentType.getString(context, "LitematicaFilesName").split(","));
                                            Optional<Project> projectOpt = projects.stream()
                                                    .filter(p -> p.ProjectName.equals(projectName))
                                                    .findFirst();
                                            if (projectOpt.isPresent()) {
                                                Project project = projectOpt.get();
                                                project.LitematicaFilesName.add(litematicaFilesName.get(0));
                                                context.getSource().getPlayer().sendMessage(new LiteralText("Litematica Files Name add"), false);
                                                return 1;
                                            } else {
                                                context.getSource().sendError(new LiteralText("Project not found"));
                                                return 0;
                                            }
                                        }))))
                .then(CommandManager.literal("setDuration")
                        .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                .then(CommandManager.argument("duration", StringArgumentType.string())
                                        .executes(context -> {
                                            String projectName = StringArgumentType.getString(context, "ProjectName");
                                            int duration = Integer.parseInt(StringArgumentType.getString(context, "duration"));
                                            Optional<Project> projectOpt = projects.stream()
                                                    .filter(p -> p.ProjectName.equals(projectName))
                                                    .findFirst();
                                            if (projectOpt.isPresent()) {
                                                Project project = projectOpt.get();
                                                project.duration = duration;
                                                context.getSource().getPlayer().sendMessage(new LiteralText("Duration set"), false);
                                                return 1;
                                            } else {
                                                context.getSource().sendError(new LiteralText("Project not found"));
                                                return 0;
                                            }
                                        }))))
                .then(CommandManager.literal("setMaterialAreaCoordinates")
                        .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                .then(CommandManager.argument("x", StringArgumentType.string())
                                        .then(CommandManager.argument("y", StringArgumentType.string())
                                                .then(CommandManager.argument("z", StringArgumentType.string())
                                                        .executes(context -> {
                                                            String projectName = StringArgumentType.getString(context, "ProjectName");
                                                            long x = Long.parseLong(StringArgumentType.getString(context, "x"));
                                                            long y = Long.parseLong(StringArgumentType.getString(context, "y"));
                                                            long z = Long.parseLong(StringArgumentType.getString(context, "z"));
                                                            Optional<Project> projectOpt = projects.stream()
                                                                    .filter(p -> p.ProjectName.equals(projectName))
                                                                    .findFirst();
                                                            if (projectOpt.isPresent()) {
                                                                Project project = projectOpt.get();
                                                                project.materialAreaCoordinates[0] = x;
                                                                project.materialAreaCoordinates[1] = y;
                                                                project.materialAreaCoordinates[2] = z;
                                                                context.getSource().getPlayer().sendMessage(new LiteralText("Material Area Coordinates set"), false);
                                                                return 1;
                                                            } else {
                                                                context.getSource().sendError(new LiteralText("Project not found"));
                                                                return 0;
                                                            }
                                                        }))))))
                .then(CommandManager.literal("setPreProject")
                .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                        .then(CommandManager.argument("PreProjectName", StringArgumentType.string())
                                .executes(context -> {
                                    String projectName = StringArgumentType.getString(context, "ProjectName");
                                    String preProjectName = StringArgumentType.getString(context, "PreProjectName");
                                    Optional<Project> projectOpt = projects.stream()
                                            .filter(p -> p.ProjectName.equals(projectName))
                                            .findFirst();
                                    Optional<Project> preProjectOpt = projects.stream()
                                            .filter(p -> p.ProjectName.equals(preProjectName))
                                            .findFirst();
                                    if (projectOpt.isPresent() && preProjectOpt.isPresent()) {
                                        Project project = projectOpt.get();
                                        project.PreProject = preProjectOpt.get();
                                        context.getSource().getPlayer().sendMessage(new LiteralText("PreProject set"), false);
                                        return 1;
                                    } else {
                                        context.getSource().sendError(new LiteralText("Project or PreProject not found"));
                                        return 0;
                                    }
                                }))))
                .then(CommandManager.literal("setSubProject")
                        .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                .then(CommandManager.argument("SubProjectName", StringArgumentType.string())
                                        .executes(context -> {
                                            String projectName = StringArgumentType.getString(context, "ProjectName");
                                            String subProjectName = StringArgumentType.getString(context, "SubProjectName");
                                            Optional<Project> projectOpt = projects.stream()
                                                    .filter(p -> p.ProjectName.equals(projectName))
                                                    .findFirst();
                                            Optional<Project> subProjectOpt = projects.stream()
                                                    .filter(p -> p.ProjectName.equals(subProjectName))
                                                    .findFirst();
                                            if (projectOpt.isPresent() && subProjectOpt.isPresent()) {
                                                Project project = projectOpt.get();
                                                project.SubProject = subProjectOpt.get();
                                                context.getSource().getPlayer().sendMessage(new LiteralText("SubProject set"), false);
                                                return 1;
                                            } else {
                                                context.getSource().sendError(new LiteralText("Project or SubProject not found"));
                                                return 0;
                                            }
                                        }))))
                .then(CommandManager.literal("setNote")
                        .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                .then(CommandManager.argument("Note", StringArgumentType.string())
                                        .executes(context -> {
                                            String projectName = StringArgumentType.getString(context, "ProjectName");
                                            String note = StringArgumentType.getString(context, "Note");
                                            Optional<Project> projectOpt = projects.stream()
                                                    .filter(p -> p.ProjectName.equals(projectName))
                                                    .findFirst();
                                            if (projectOpt.isPresent()) {
                                                Project project = projectOpt.get();
                                                project.Note = note;
                                                context.getSource().getPlayer().sendMessage(new LiteralText("Note set"), false);
                                                return 1;
                                            } else {
                                                context.getSource().sendError(new LiteralText("Project not found"));
                                                return 0;
                                            }
                                        }))))
                .then(CommandManager.literal("setStatus")
                        .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                .then(CommandManager.argument("Status", StringArgumentType.string())
                                        .executes(context -> {//aa我先告诉你怎么触发的ok
                                            //进游戏，然后进一个存档，然后点保存并退出，显示Saving world然后就crash了
                                            String projectName = StringArgumentType.getString(context, "ProjectName");
                                            short status = Short.parseShort(StringArgumentType.getString(context, "Status"));
                                            Optional<Project> projectOpt = projects.stream()
                                                    .filter(p -> p.ProjectName.equals(projectName))
                                                    .findFirst();
                                            if (projectOpt.isPresent()) {
                                                Project project = projectOpt.get();
                                                project.Status = status;
                                                context.getSource().getPlayer().sendMessage(new LiteralText("Status set"), false);
                                                return 1;
                                            } else {
                                                context.getSource().sendError(new LiteralText("Project not found"));
                                                return 0;
                                            }
                                        }))))
                .then(CommandManager.literal("ProjectDetails")
                        .then(CommandManager.argument("ProjectName", StringArgumentType.string())
                                .executes(context -> {
                                    String projectName = StringArgumentType.getString(context, "ProjectName");
                                    new DetailsDisplay(projectName,context.getSource());
                                    return 1;
                                }))));
    }
}