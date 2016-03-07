package net.aimeizi;

import com.taobao.common.tfs.DefaultTfsManager;
import com.taobao.common.tfs.TfsManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private static App instance = null;

    ClassPathXmlApplicationContext appContext = null;
    TfsManager tfsManager = null;

    private App() {
        appContext = new ClassPathXmlApplicationContext(new String[]{"tfs.xml"});
        tfsManager = (DefaultTfsManager) appContext.getBean("tfsManager");
    }

    static App getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new App();
            return instance;
        }
    }

    public TfsManager getTfsManager() {
        return tfsManager;
    }

    /**
     * 不传入suffix和tfsname
     *
     * @param localfile 要上传的文件
     */
    static String save_file_without_param(String localfile) {
        String tfsname = App.getInstance().getTfsManager().saveFile(localfile, null, null);
        System.out.println("save localfile " + localfile + " without tfsname and suffix  tfsname is " + tfsname);
        return tfsname;
    }

    /**
     * 只传入suffix
     *
     * @param localfile
     * @param suffix
     */
    static String save_file_with_suffix(String localfile, String suffix) {
        String tfsname = App.getInstance().getTfsManager().saveFile(localfile, null, suffix);
        System.out.println("save localfile " + localfile + " with suffix " + suffix + " tfsname is " + tfsname);
        return tfsname;
    }

    /**
     * 只传入tfsname
     *
     * @param localfile
     * @param tfsname
     */
    static String save_file_with_tfsname(String localfile, String tfsname) {
        String newtfsname = App.getInstance().getTfsManager().newTfsFileName(tfsname);
        tfsname = App.getInstance().getTfsManager().saveFile(localfile, newtfsname, null);
        System.out.println("save localfile " + localfile + " with tfsname " + newtfsname + " tfsname is " + tfsname);
        return tfsname;
    }

    /**
     * 同时传入suffix和tfsname
     *
     * @param localfile
     * @param tfsname
     * @param suffix
     */
    static String save_file_with_tfsname_suffix(String localfile, String tfsname, String suffix) {
        String newtfsname = App.getInstance().getTfsManager().newTfsFileName(tfsname);
        tfsname = App.getInstance().getTfsManager().saveFile(localfile, newtfsname, suffix);
        System.out.println("save localfile " + localfile + " with tfsname " + newtfsname + " and suffix " + suffix + " tfsname is " + tfsname);
        return tfsname;
    }

    /**
     * 读文件，不带后缀名
     *
     * @param tfsname
     * @param tmpfile
     */
    static boolean fetch_file_without_param(String tfsname, String tmpfile) {
        return App.getInstance().getTfsManager().fetchFile(tfsname, null, tmpfile);
    }

    /**
     * 读文件，带后缀名
     *
     * @param tfsname
     * @param tmpfile
     */
    static boolean fetch_file_with_suffix(String tfsname, String suffix, String tmpfile) {
        return App.getInstance().getTfsManager().fetchFile(tfsname, suffix, tmpfile);
    }

    /**
     * 删除文件
     *
     * @param tfsname
     * @param suffix
     * @return
     */
    static boolean delete(String tfsname, String suffix) {
        return App.getInstance().getTfsManager().unlinkFile(tfsname, suffix);
    }

    /**
     * 隐藏或显示文件
     *
     * @param tfsname
     * @param suffix
     * @param option  //1 隐藏；0显示
     * @return
     */
    static boolean hide(String tfsname, String suffix, int option) {
        return App.getInstance().getTfsManager().hideFile(tfsname, suffix, option);
    }


    public static void main(String[] args) {

        String basedir = System.getProperty("user.dir").replace("\\", "/");

        String localfile1 = basedir + "/src/main/resources/common-tfs-2.1.6.jar";
        System.out.println(localfile1);

        String localfile2 = basedir + "/src/main/resources/bd_logo.png";
        System.out.println(localfile2);

        //*****写文件*****

        // 1。不传入suffix和tfsname

        String tfsname1 = save_file_without_param(localfile1);
        System.out.println(tfsname1);
//        fetch_file_without_param(tfsname1,"common-tfs-2.1.6.jar");
        String tfsname2 = save_file_without_param(localfile2);
        System.out.println(tfsname2);
//        fetch_file_without_param(tfsname2,"bd_logo.png");
        // 2。只传入suffix

        String tfsname3 = save_file_with_suffix(localfile1, ".jar");
        System.out.println(tfsname3);
        String tfsname4 = save_file_with_suffix(localfile2, ".png");
        System.out.println(tfsname4);

        // 3。只传入tfsname

        String tfsname5 = save_file_with_tfsname(localfile1, "common-tfs-2.1.6.jar");
        System.out.println(tfsname5);
        String tfsname6 = save_file_with_tfsname(localfile2, "bd_logo.png");
        System.out.println(tfsname6);

        // 4。同时传入suffix和tfsname

        String tfsname7 = save_file_with_tfsname_suffix(localfile1, "common-tfs-2.1.6.jar", ".jar");
        System.out.println(tfsname7);
//        fetch_file_with_suffix(tfsname7,".jar","common-tfs-2.1.6.jar");
        String tfsname8 = save_file_with_tfsname_suffix(localfile2, "bd_logo.png", ".png");
        System.out.println(tfsname8);
//        fetch_file_with_suffix(tfsname8,".png","bd_logo.png");

        System.exit(0);
    }
}
