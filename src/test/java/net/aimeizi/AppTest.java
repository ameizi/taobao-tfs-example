package net.aimeizi;

import com.taobao.common.tfs.TfsManager;
import com.taobao.common.tfs.packet.FileInfo;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by fengjing on 2016/3/7.
 */
public class AppTest extends TestCase {

    ApplicationContext context;

    protected void setUp() throws Exception {
        super.setUp();
        context = new ClassPathXmlApplicationContext("tfs.xml");
    }

    public void testTfsManager() {
        TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
        System.out.println(tfsMgr);
    }

    /**
     * 存文件
     */
    public void testSave() {
        String basedir = System.getProperty("user.dir").replace("\\", "/");
        String localfile = basedir + "/src/main/resources/bd_logo.png";
        TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
        String fileName = tfsMgr.saveFile(localfile, null, null);
        System.out.println(fileName);
    }

    /**
     * 读文件
     */
    public void testFetch() {
        TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
        boolean flag = tfsMgr.fetchFile("T1IyJTByJT1RCvBVdK", null, "bd_logo.png");
        System.out.println(flag);
    }

    /**
     * 删文件
     */
    public void testDelete() {
        TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
        boolean flag = tfsMgr.unlinkFile("T1IyJTByJT1RCvBVdK", null);
        System.out.println(flag);
    }

    /**
     * 隐藏文件
     */
    public void testHide() {
        //1 隐藏；0显示
        TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
        boolean flag = tfsMgr.hideFile("T1IyJTByJT1RCvBVdK", null, 1);
        System.out.println(flag);
    }

    /**
     * 查看FileInfo
     */
    public void testState() {
        TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
        FileInfo fileInfo = tfsMgr.statFile("T1IyJTByJT1RCvBVdK", null);
        int flag = fileInfo.getFlag();
        System.out.println(flag);
    }

    /**
     * LargeFile读写
     */
    public void testLargeFile() {
        TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
        String tfsname = tfsMgr.saveLargeFile("研磨设计模式.pdf", null, null);
        System.out.println(tfsname);
        FileInfo fileInfo = tfsMgr.statFile(tfsname, null);
        System.out.println("id:" + fileInfo.getId() + "\ncreateTime:" + fileInfo.getCreateTime() + "\nmodifyTime:" + fileInfo.getModifyTime() + "\nlength:" + fileInfo.getLength());
        tfsMgr.fetchFile(tfsname, null, "bak.pdf");
    }

}

