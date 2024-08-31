# ProjectManager

此项目用于管理Minecraft服务器上的项目。它提供了一些命令和功能来帮助玩家管理和跟踪他们的项目进度。

## 功能

- **设置项目备注**: 允许玩家为项目设置备注。
    - 命令: `/projectmanager setNote <ProjectName> <Note>`
    - 示例: `/projectmanager setNote MyProject "This is a note"`

- **设置项目状态**: 允许玩家为项目设置状态。
    - 命令: `/projectmanager setStatus <ProjectName> <Status>`
    - 示例: `/projectmanager setStatus MyProject 1`

- **显示项目详情**: 允许玩家查看项目的详细信息。
    - 命令: `/projectmanager ProjectDetails <ProjectName>`
    - 示例: `/projectmanager ProjectDetails MyProject`


- **~~以及还有一些命令懒得写了请您自行翻阅代码~~**


- **玩家下线通知**: 当玩家下线时，通知其他玩家该玩家背包中的项目材料数量。
    - 功能描述: 当玩家下线时，系统会检查玩家背包中的物品，并通知其他玩家该玩家背包中属于项目材料清单中的物品数量（除去潜影盒）以及潜影盒的数量。

## 安装

1. 克隆此仓库到本地：
    ```sh
    git clone https://github.com/yourusername/ProjectManager.git
    ```
2. 使用Gradle构建项目：
    ```sh
    cd ProjectManager
    ./gradlew build
    ```

## 使用

1. 将构建生成的JAR文件放入Minecraft服务器的`mods`文件夹中。
2. 启动Minecraft服务器。
3. 使用以下命令来管理项目：

## 贡献

欢迎贡献代码！请提交Pull Request或报告问题。

## 许可证

此项目使用MIT许可证。有关详细信息，请参阅`LICENSE`文件。