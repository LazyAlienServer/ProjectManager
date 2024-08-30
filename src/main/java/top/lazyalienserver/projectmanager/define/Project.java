package top.lazyalienserver.projectmanager.define;

import java.util.List;

public class Project {
    public String ProjectName;
    public long[] ProjectPlacePOS= new long[3];//[0]:x  [1]:y  [2]:z
    public List<String> projectPersonnel;
    public List<String> projectLeader;
    public short Status=0;//0:未审批 未完工 || 1:已审批 未完工 || 2:已审批 已完工 || -1:已取消 || -2:未审批 已完工
    public List<String> LitematicaFilesName;
    public int duration=0;//工期
    public long[] materialAreaCoordinates= new long[3];//[0]:x  [1]:y  [2]:z
    public Project PreProject=null;//前置项目
    public Project SubProject=null;//子项目
    public String Note;//注解
}
