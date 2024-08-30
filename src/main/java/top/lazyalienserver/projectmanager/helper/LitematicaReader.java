// 本文件有使用Code with me对话的珍贵记录.jpg
package top.lazyalienserver.projectmanager.helper;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Paths;

public class LitematicaReader {
    static final Logger logger = LoggerFactory.getLogger("LitematicaReader");

    String content;
    NbtCompound nbtCompound;

    public LitematicaReader(String path) throws IOException {
        logger.info("File content: {}", content);
        //nbtCompound = StringNbtReader.parse(content);
        //读出来是nbt就这个嘛应该是吧试试试试（但是为什么在关的时候crash 好了吗
        File file = Paths.get(path).toFile();
        InputStream is = new FileInputStream(file);
        try
        {
            nbtCompound = NbtIo.readCompressed(is);
        }
        catch (Exception e)
        {
            try
            {
                is.close();
                is = new FileInputStream(file);
                nbtCompound = NbtIo.read(new DataInputStream(is));
            }
            catch (Exception ignore) {}
        }
        //NbtIo.readCompressed()换掉试一下
        //tmd又crash 你能看到运行的内容吗没有:F 爆的一样的吗zy 这回不一样了 看看 哦没解压哦呸解压了
        //所以是解压的问题吗 再试试ok等会我调用的是getContent。。。我改一下content里面是什么现在变成空的了o
        //好了
        //是什么问题啊投影的反序列化出问题了简单来说，就是解析炸了:F读文件就crash?bud
        //🤔
        //给点建议呗哪一部分比如选区读容器                          ok gson和jackson你选哪个 🤔gson?gson用起来简单，但是jackson可以忽略字段，ok 就用gson吧，我们够用了ok
        //还有就是我想吧那个List<Project>关服给存到json里,开服读出来哦，那你用库gson或者别的都可以，注意的一点是，别序列化炸了（比如说这个对象调用那个对象，那个对象又调用回来，这样会爆栈的递归爆栈
    }
    public String getContent() {
        return nbtCompound.toString();
    }
    public NbtCompound getNbtCompound() {
        return nbtCompound;
    }
}
