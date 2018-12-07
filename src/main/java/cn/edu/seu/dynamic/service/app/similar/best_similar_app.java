package cn.edu.seu.dynamic.service.app.similar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class best_similar_app {
    static int app_number = 203561;                         //APP数
    static int character_number = 13;                       //APP特征个数
    static int k = 5;                                       //K近邻的k值选取

    static int bucket_length = 5;                           //hash编码每5个连续作为一个桶
    List<List<String>> buckets = new ArrayList<>();         //hash得到的所有桶

    public static void main(String[] args) {
        String filename = "C:\\Users\\xul\\Desktop\\aa\\data_UTF8.txt";       //数据存放位置。
        String[][] app_array = load_data(filename);                       //把数据读入数组。
        String[][] trainning_data = get_trainning_data(app_array);        //取前二十万个APP做为训练数据
        String[][] test_data = get_test_data(app_array);                  //取后3000个数据作为测试数据
        String result_filename = "C:\\Users\\xul\\Desktop\\aa\\result.txt";   //结果存放位置。
        test(trainning_data, test_data, result_filename);                    //测试 并将结果存入文件
    }


    private static void app_knn(String[][] app_array, String[] test_app, String[][] k_nearest_app) {//求与测试app最相似的k个app
        double age_dif = age_max_dif(app_array);                                //输入整理后的数据和测试数据
        double download_dif = download_max_dif(app_array);                      //以及存放最近邻的数组
        double[] distance = new double[app_array.length];
        for (int i = 0; i < app_array.length; i++) {
            double d3 = 1;
            double d4 = 1;
            double d5 = 0;
            double d6 = 1;
            double d7 = 1;
            double d8 = 1;
            double d9 = 0;
            if (test_app[3] != null && test_app[3].equals(app_array[i][3])) {
                d3 = 0.3;
            }

            if (test_app[4] != null && test_app[4].equals(app_array[i][4])) {
                d4 = 0.3;
            }
            if (test_app[6] != null && test_app[6].equals(app_array[i][6])) {
                d6 = 0.8;
            }
            if (test_app[7] != null && test_app[7].equals(app_array[i][7])) {
                d7 = 0.8;
            }
            if (test_app[8] != null && test_app[8].equals(app_array[i][8])) {
                d8 = 0.6;
            }
            d5 = Double.valueOf(test_app[5]) - Double.valueOf(app_array[i][5]);
            d5 = Math.abs(d5) / age_dif;
            d9 = Double.valueOf(test_app[9]) - Double.valueOf(app_array[i][9]);
            d9 = Math.abs(d9) / download_dif;
            distance[i] = Math.sqrt(Math.pow(d3, 2) * 0.1 + Math.pow(d4, 2) * 0.1 + Math.pow(d5, 2) * 0.5 + Math.pow(d6, 2) * 0.9 + Math.pow(d7, 2) * 0.9 + Math.pow(d8, 2) * 0.5 + Math.pow(d9, 2) * 0.9);
            distance[i] = distance[i] * 1000000000;
        }
        int[] label = new int[app_array.length];
        for (int i = 0; i < app_array.length; i++) {
            label[i] = i;
        }
        sort(distance, label);
        System.out.println("______________________");
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < character_number; j++) {
                k_nearest_app[i][j] = app_array[label[i]][j];
            }
        }
    }

    public static void preprocessing(String[][] app_array) {
        for (int i = 0; i < app_array.length; i++) {

        }
    }

    /**
     * 对app_knn的优化版
     *
     * @param app_array
     * @param test_app
     * @param k_nearest_app
     */
    private static void app_knn_pro(String[][] app_array, String[] test_app, String[][] k_nearest_app) {//求与测试app最相似的k个app
        double age_dif = age_max_dif(app_array);                                //输入整理后的数据和测试数据
        double download_dif = download_max_dif(app_array);                      //以及存放最近邻的数组
        double[] distance = new double[app_array.length];
        int[] label = new int[app_array.length];

        for (int i = 0; i < app_array.length; i++) {
            double d3 = 1;
            double d4 = 1;
            double d5 = 0;
            double d6 = 1;
            double d7 = 1;
            double d8 = 1;
            double d9 = 0;
            if (test_app[3] != null && test_app[3].equals(app_array[i][3])) {
                d3 = 0.3;
            }

            if (test_app[4] != null && test_app[4].equals(app_array[i][4])) {
                d4 = 0.3;
            }
            if (test_app[6] != null && test_app[6].equals(app_array[i][6])) {
                d6 = 0.8;
            }
            if (test_app[7] != null && test_app[7].equals(app_array[i][7])) {
                d7 = 0.8;
            }
            if (test_app[8] != null && test_app[8].equals(app_array[i][8])) {
                d8 = 0.6;
            }
            d5 = Double.valueOf(test_app[5]) - Double.valueOf(app_array[i][5]);
            d5 = Math.abs(d5) / age_dif;
            d9 = Double.valueOf(test_app[9]) - Double.valueOf(app_array[i][9]);
            d9 = Math.abs(d9) / download_dif;
            distance[i] = Math.sqrt(Math.pow(d3, 2) * 0.1 + Math.pow(d4, 2) * 0.1 + Math.pow(d5, 2) * 0.5 + Math.pow(d6, 2) * 0.9 + Math.pow(d7, 2) * 0.9 + Math.pow(d8, 2) * 0.5 + Math.pow(d9, 2) * 0.9);
            distance[i] = distance[i] * 1000000000;
            label[i] = i;
        }

        sort(distance, label);
        System.out.println("______________________");
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < character_number; j++) {
                k_nearest_app[i][j] = app_array[label[i]][j];
            }
        }
    }


    private static String[][] load_data(String filename) {                        //导入并处理数据
        String[][] app_array = new String[app_number + 2][character_number];      //输入三元组文件 输出处理后的二维数组
        File file = new File(filename);                                             //输出的二维数组格式 一行代表一个APP 一列代表一个特征  共有13列
        BufferedReader reader = null;                                               //对数据的处理包括补全功能 提取年龄和下载量的数值 并用均值替代年龄和下载量的NULL
        String temp = null;
        int line = 0;
        String tag_pre = "<http://DynamicAPP/instance/Apple Store_ios_5.0>";
        String tag_now = null;
        String[] temp_line = new String[3];
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((temp = reader.readLine()) != null) {
                temp_line = temp.split("###	###");
                temp_line[0] = temp_line[0].replace("\"", "");
                temp_line[1] = temp_line[1].replace("\"", "");
                temp_line[2] = temp_line[2].replace("\"", "");
                tag_now = temp_line[0];
                if (!tag_now.equals(tag_pre)) {
                    line++;
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#ID>")) {
                    app_array[line][0] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#名称>")) {
                    app_array[line][1] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#版本号>")) {
                    app_array[line][2] = temp_line[2];
                }
                if (temp_line[1].equals("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>")) {
                    app_array[line][3] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#rdf:type>") || temp_line[1].equals("<http://DynamicAPP/property#rdf: type>")) {
                    app_array[line][4] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#年龄限制>")) {
                    app_array[line][5] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#版权所属>")) {
                    app_array[line][6] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#使用平台>")) {
                    app_array[line][7] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#公司>")) {
                    app_array[line][8] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#下载量>")) {
                    app_array[line][9] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#最新更新时间>")) {
                    app_array[line][10] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/property#描述>")) {
                    app_array[line][12] = temp_line[2];
                }
                if (temp_line[1].equals("<http://DynamicAPP/relation#开发>")) {
                    if (app_array[line][11] != null) {
                        app_array[line][11] = app_array[line][11] + "###   ###" + temp_line[2];
                    } else {
                        app_array[line][11] = temp_line[2];
                    }
                }
                tag_pre = tag_now;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Extract_number(app_array);
        String[][] app_array_2 = new String[app_number][character_number];
        app_array[2][0] = app_array[1][0];
        for (int i = 0; i < app_array_2.length; i++) {
            for (int j = 0; j < character_number; j++) {
                app_array_2[i][j] = app_array[i + 2][j];
            }
        }
        complete_age_download(app_array_2);
        comeplete_class(app_array_2);
        return app_array_2;
    }


    public static String get_number_from_String(String str_with_number)  //从字符串中提取数字
    {                                                                   //输入带有数字的字符串 输出纯数字字符串
        str_with_number = str_with_number.trim();
        String str2 = "";
        if (str_with_number != null && !"".equals(str_with_number)) {
            for (int i = 0; i < str_with_number.length(); i++) {
                if (str_with_number.charAt(i) >= 48 && str_with_number.charAt(i) <= 57) {
                    str2 += str_with_number.charAt(i);
                }
            }
        }
        return str2;
    }


    public static void Extract_number(String[][] app_array)  //提取数组app_array年龄限制和下载量中数字。
    {                                                        //输入提取的数组数据
        for (int i = 0; i < app_array.length; i++) {
            if (app_array[i][5] != null) {
                app_array[i][5] = get_number_from_String(app_array[i][5]);
            }
            if (app_array[i][9] != null) {
                app_array[i][9] = get_number_from_String(app_array[i][9]);
            }
        }
    }


    public static void save_data(String[][] app_array)  //把提取出来的数组数据存入桌面txt
    {
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(new File("C:\\Users\\asus\\Desktop\\processed_data_1.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintStream p = new PrintStream(fs);
        for (int i = 0; i < app_array.length; i++) {
            p.println(app_array[i][0] + "###   ###" +
                    app_array[i][1] + "###   ###" +
                    app_array[i][2] + "###   ###" +
                    app_array[i][3] + "###   ###" +
                    app_array[i][4] + "###   ###" +
                    app_array[i][5] + "###   ###" +
                    app_array[i][6] + "###   ###" +
                    app_array[i][7] + "###   ###" +
                    app_array[i][8] + "###   ###" +
                    app_array[i][9] + "###   ###" +
                    app_array[i][10] + "###   ###" +
                    app_array[i][11] + "###   ###" +
                    app_array[i][12]);
        }
        p.close();
    }


    private static void comeplete_class(String[][] app_array) {              //补全数据中类别为null的值
        for (int i = 0; i < app_array.length; i++)                                  //输入数组数据
        {
            if (app_array[i][3] == null || app_array[i][3].equals("") || app_array[i][3].equals("null")) {
                if (app_array[i][4].equals("益智解谜") || app_array[i][4].equals("角色扮演游戏") ||
                        app_array[i][4].equals("卡牌游戏") || app_array[i][4].equals("游戏") ||
                        app_array[i][4].equals("冒险游戏") || app_array[i][4].equals("娱乐场游戏") ||
                        app_array[i][4].equals("桌面游戏") || app_array[i][4].equals("模拟游戏") ||
                        app_array[i][4].equals("策略游戏") || app_array[i][4].equals("竞速游戏") ||
                        app_array[i][4].equals("文字游戏") || app_array[i][4].equals("动作游戏") ||
                        app_array[i][4].equals("问答游戏") || app_array[i][4].equals("聚会游戏") ||
                        app_array[i][4].equals("街机游戏") || app_array[i][4].equals("角色扮演") ||
                        app_array[i][4].equals("动作") || app_array[i][4].equals("街机") ||
                        app_array[i][4].equals("冒险") || app_array[i][4].equals("益智") ||
                        app_array[i][4].equals("赌场") || app_array[i][4].equals("策略") ||
                        app_array[i][4].equals("卡牌") || app_array[i][4].equals("竞速") || app_array[i][4].equals("桌面和棋类")) {
                    app_array[i][3] = "游戏类App";
                }
                if (app_array[i][4].equals("音乐") || app_array[i][4].equals("体育") ||
                        app_array[i][4].equals("美食佳饮") || app_array[i][4].equals("娱乐") ||
                        app_array[i][4].equals("天气") || app_array[i][4].equals("购物") ||
                        app_array[i][4].equals("旅游") || app_array[i][4].equals("贴纸") ||
                        app_array[i][4].equals("健康健美") || app_array[i][4].equals("医疗") ||
                        app_array[i][4].equals("生活") || app_array[i][4].equals("工具") ||
                        app_array[i][4].equals("导航") || app_array[i][4].equals("参考") ||
                        app_array[i][4].equals("效率") || app_array[i][4].equals("音乐与音频") ||
                        app_array[i][4].equals("体育") || app_array[i][4].equals("生活时尚") ||
                        app_array[i][4].equals("家居装修") || app_array[i][4].equals("餐饮美食") ||
                        app_array[i][4].equals("健康与健身") || app_array[i][4].equals("旅游与本地出行") ||
                        app_array[i][4].equals("活动") || app_array[i][4].equals("地图和导航") ||
                        app_array[i][4].equals("漫画") || app_array[i][4].equals("休闲") ||
                        app_array[i][4].equals("个性化") || app_array[i][4].equals("美容时尚") ||
                        app_array[i][4].equals("车辆和交通") || app_array[i][4].equals("娱乐  ")) {
                    app_array[i][3] = "生活类App";
                }
                if (app_array[i][4].equals("摄影与录像") || app_array[i][4].equals("视频") ||
                        app_array[i][4].equals("摄影") || app_array[i][4].equals("图形和设计") ||
                        app_array[i][4].equals("艺术和设计") || app_array[i][4].equals("视频播放和编辑")) {
                    app_array[i][3] = "图像与视频类App";
                }
                if (app_array[i][4].equals("教育") || app_array[i][4].equals("医学") ||
                        app_array[i][4].equals("图书") || app_array[i][4].equals("育儿") ||
                        app_array[i][4].equals("文字") || app_array[i][4].equals("图书与工具书") ||
                        app_array[i][4].equals("知识问答")) {
                    app_array[i][3] = "教育类App";
                }
                if (app_array[i][4].equals("商务") || app_array[i][4].equals("财务") ||
                        app_array[i][4].equals("软件开发工具") || app_array[i][4].equals("财经") ||
                        app_array[i][4].equals("工具") || app_array[i][4].equals("软件库与演示") ||
                        app_array[i][4].equals("模拟") || app_array[i][4].equals("公司") ||
                        app_array[i][4].equals("商务办公")) {
                    app_array[i][3] = "工具类App";
                }
                if (app_array[i][4].equals("新闻") || app_array[i][4].equals("新闻杂志")) {
                    app_array[i][3] = "新闻类App";
                }
                if (app_array[i][4].equals("报刊杂志")) {
                    app_array[i][3] = "报刊杂志类App";
                }
                if (app_array[i][4].equals("社交") || app_array[i][4].equals("通讯") ||
                        app_array[i][4].equals("社交约会")) {
                    app_array[i][3] = "社交类App";
                }
            }
        }

    }


    public static double age_max_dif(String[][] app_array) {  //求age最大值与最小值之差
        double dif = 0;                                      //输入数组数据 输出差值
        double max = 0;
        double min = 18;
        for (int i = 0; i < app_array.length; i++) {
            if (Double.valueOf(app_array[i][5]) > max) {
                max = Double.valueOf(app_array[i][5]);
            }
            if (Double.valueOf(app_array[i][5]) < min) {
                min = Double.valueOf(app_array[i][5]);
            }
        }
        dif = max - min;
        return dif;
    }


    public static double download_max_dif(String[][] app_array) {  //求下载量最大值与最小值之差
        double dif = 0;
        double max = 0;
        double min = 10000;
        for (int i = 0; i < app_array.length; i++) {
            if (Double.valueOf(app_array[i][9]) > max) {
                max = Double.valueOf(app_array[i][9]);
            }
            if (Double.valueOf(app_array[i][9]) < min) {
                min = Double.valueOf(app_array[i][9]);
            }
        }
        dif = max - min;
        return dif;
    }


    public static void complete_age_download(String[][] app_array) {//用均值补全年龄和下载量的null值
        double age_average = 0;
        double download_average = 0;
        for (int i = 0; i < app_array.length; i++) {
            if (app_array[i][5] != null && !app_array[i][5].equals("") && !app_array[i][5].equals("null")) {
                Integer it = new Integer(app_array[i][5]);
                int temp = it.intValue();
                age_average = age_average + temp;
            }
            if (app_array[i][9] != null && !app_array[i][9].equals("") && !app_array[i][9].equals("null")) {
                Integer it = new Integer(app_array[i][9]);
                int temp = it.intValue();
                download_average = download_average + temp;
            }
        }
        age_average = (age_average * 1.0) / app_array.length;
        download_average = (download_average * 1.0) / app_array.length;
        for (int i = 0; i < app_array.length; i++) {
            if (app_array[i][5] == null || app_array[i][5].equals("") || app_array[i][5].equals("null")) {
                app_array[i][5] = String.valueOf(age_average);
            }
            if (app_array[i][9] == null || app_array[i][9].equals("") || app_array[i][9].equals("null")) {
                app_array[i][9] = String.valueOf(download_average);
            }
        }
    }


    public static void bubbleSort(double[] a, int[] b) {                  //排序算法
        double temp;
        int temp_1;
        int size = a.length;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    temp_1 = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = temp_1;
                }
            }
        }
    }


    private static String[][] get_trainning_data(String[][] app_array) {      //取前二十万个APP做为训练数据
        String[][] trainning_data = new String[200000][character_number];
        for (int i = 0; i < trainning_data.length; i++) {
            for (int j = 0; j < character_number; j++) {
                trainning_data[i][j] = app_array[i][j];
            }
        }
        return trainning_data;
    }


    private static String[][] get_test_data(String[][] app_array) {             //取后3000个数据作为测试数据
        String[][] test_data = new String[3000][character_number];
        for (int i = 0; i < test_data.length; i++) {
            for (int j = 0; j < character_number; j++) {
                test_data[i][j] = app_array[200000 + i][j];
            }
        }
        return test_data;
    }


    private static void test(String[][] trainning_data, String[][] test_data, String result_filename) { //测试算法
        String[][] k_nearest_app = new String[k][character_number];                                //输入训练数据数组，测试数据数组，和输出文件位置
        FileOutputStream fs = null;                                                                  //输出 结果txt文档
        try {
            fs = new FileOutputStream(new File(result_filename), false);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintStream p = new PrintStream(fs);

        for (int i = 0; i < test_data.length; i++) {
            app_knn(trainning_data, test_data[i], k_nearest_app);
            p.println("测试APP为：");
            for (int j = 0; j < character_number - 2; j++) {
                p.print(test_data[i][j] + "\t");
            }
            p.println(" ");

            for (int a = 0; a < k; a++) {
                p.println("离" + test_data[i][1] + "第" + (a + 1) + "近的app是：");
                for (int b = 0; b < character_number - 2; b++) {
                    p.print(k_nearest_app[a][b] + "\t");
                }
                p.println(" ");
            }

        }

        p.close();
    }

    private static double[] sort(double[] a, int[] b, int low, int high) {//归并排序
        int mid = (low + high) / 2;                                    //输入排序数组，排序数组下标数组，排序起点下标，排序终点下标
        if (low < high) {                                              //返回排序好的数组
            // 左边数组
            sort(a, b, low, mid);
            // 右边数组
            sort(a, b, mid + 1, high);
            // 左右归并
            merge(a, b, low, mid, high);
        }
        return a;
    }

    private static void merge(double[] a, int[] b, int low, int mid, int high) {  //归并排序中合并算法
        double[] temp = new double[high - low + 1];
        int[] temp_1 = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp_1[k] = b[i];
                temp[k++] = a[i++];
            } else {
                temp_1[k] = b[j];
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp_1[k] = b[i];
            temp[k++] = a[i++];
        }
        while (j <= high) {
            temp_1[k] = b[j];
            temp[k++] = a[j++];
        }
        for (int m = 0; m < temp.length; m++) {
            a[m + low] = temp[m];
            b[m + low] = temp_1[m];
        }
    }

    public static double[] sort(double[] a, int[] b) {          //归并排序
        return sort(a, b, 0, a.length - 1);
    }

}
