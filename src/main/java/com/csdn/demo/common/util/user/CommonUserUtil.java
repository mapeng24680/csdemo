package com.csdn.demo.common.util.user;

import com.alibaba.druid.util.StringUtils;
import org.springframework.stereotype.Component;

import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommonUserUtil {

    private static final ThreadLocal<Object[]> WEBCONTEXT_USER = new ThreadLocal();

    /**
     * @author stone
     * @function 时间加减
     */
    public enum DType {

        SECOND(13), MINUTE(12), DATE(5), MONTH(2), YEAR(1);

        private int type;

        private DType(int pType) {
            type = pType;
        }


        @Override
        public String toString() {
            return String.valueOf(this.type);
        }
    }

    /**
     * 登录用户的登录名
     *
     * @return
     */
    public static void registryUser(String userName, Integer userId) {
        Object[] user = (Object[]) WEBCONTEXT_USER.get();
        if (user == null) {
            user = new Object[5];
        }

        user[0] = userName;
        user[1] = userId;
        WEBCONTEXT_USER.set(user);
    }

    /**
     * 登录用户的登录名
     *
     * @return
     */
    public static Object[] getUserName() {
        return (Object[]) WEBCONTEXT_USER.get();

    }
//	public static Integer getUserId() {
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
//				.getAuthentication()
//				.getPrincipal();
//		return userDetails == null?null: Integer.parseInt(userDetails.getUsername().split("_")[1]);
//	}


    /**
     * 获取 rpc里面抛出的异常描述 <一句话功能简述> <功能详细描述>
     *
     * @param message
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getRpcBusinessExceiption(String message) {
        String regex = "com.rrkd.se.common.exception.BusinessException:(.*?)\ncom.rrkd.se.common.exception.BusinessException:";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(message);
        if (m.find()) {
            return m.group(1).trim();
        }
        return null;
    }

    public static Integer randomCode() {
        return (int) ((Math.random() * 2000 + 50) * 100000);
    }

    public static String[] getHashSetItem(HashSet<String> set) {
        if (set != null && !set.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            for (String s : set) {
                sb.append(s);
                sb.append(",");
            }
            return sb.toString().split(",");
        }
        return null;
    }


    /**
     * @param @param  pDate 需要格式化的时间
     * @param @param  pFormat 指定格式化时间格式
     * @param @return 参数说明 格式化以后的结果
     * @return String 返回类型
     * @throws
     * @Title: formatDate
     * @Description: TODO(格式化时间)
     * @Function: com.rrkd.customer.common.util.CommonUtil
     * @date 2015年9月1日 上午10:30:15
     */
    public static String formatDate(final Date pDate, final String pFormat) {
        SimpleDateFormat sdf = null;
        if (pFormat == null) {
            sdf = new SimpleDateFormat("yyyy.MM.dd");
        } else {
            sdf = new SimpleDateFormat(pFormat);
        }

        return sdf.format(pDate);
    }

    /**
     * java生成随机数字和字母组合
     *
     * @param length [生成随机数的长度]
     * @return
     */
    public static String getCharAndNumr(int length) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                str += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                str += String.valueOf(random.nextInt(10));
            }
        }
        return str.toUpperCase();
    }

    /**
     * 获取某个日期当天的特定时、分、秒时间点
     * 不传date则表示设置当前日期下的时、分、秒时间点
     *
     * @param date 日期
     * @param hour 时
     * @param min  分
     * @param
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static Date getTime(Date date, int hour, int min, int second) {
        date = date == null ? new Date() : date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 验证
     *
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static boolean validate(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 获取文件名字
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param path
     * @return
     * @author longyingan 工号
     * @see [类、类#方法、类#成员]
     */
    public static String getUrlFileName(String path) {
        if (path == null) {
            return null;
        }

        int begin = path.lastIndexOf('/');
        return path.substring(begin, path.length());
    }

    /**
     * 生成length长度的随机数字
     *
     * @param length
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static String getRandomNum(int length) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            buffer.append(String.valueOf(random.nextInt(10)));
        }
        return buffer.toString();
    }

    /**
     * 生成length长度的随机字符串
     *
     * @param length
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static String getRandomChar(int length) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            // 取得大写字母还是小写字母
            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
            buffer.append((char) (choice + random.nextInt(26)));
        }
        return buffer.toString();
    }

    /**
     * 取得区间内的double随机值
     *
     * @param start
     * @param end
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static Double getRandomDouble(double start, double end) {
        Random random = new Random();
        int r = (int) (end - start);
        int a = random.nextInt(r) + 1;
        if (random.nextBoolean()) {
            return random.nextDouble() * a + start;
        } else {
            return end - random.nextDouble() * a;
        }
    }

    /**
     * 四舍五入格式化数字
     * 123.3255 -->123.33
     * 123.3215 -->123.32
     *
     * @param dou
     * @param format
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static String getFormatDouble(Double dou, String format) {
        DecimalFormat f = new DecimalFormat(format);
        return f.format(dou);
    }


    /**
     * 字符串转日期
     *
     * @param date
     * @param format
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static Date formatStrToDate(String date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }


    /**
     * @param regular      正则表达式
     * @param validatedStr 需要被验证的字符串
     * @return
     * @description:增则匹配
     * @date:2016年7月20日下午6:34:31
     */
    public static Boolean isMatch(String regular, String validatedStr) {
        Pattern p = Pattern.compile(regular);
        return p.matcher(validatedStr).find();
    }


    public static String toStringItem(String[] item) {
        if (item != null) {
            if (item.length == 1)
                return item[0];
            StringBuffer sb = new StringBuffer();
            for (String str : item) {
                sb.append(str + ",");
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 获取指定时间所在月份的最后一天的时间
     *
     * @param datadate
     * @param dateFormat
     * @param outFormat
     * @return
     * @throws Exception
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static String getLastDay(String datadate, String dateFormat, String outFormat) {
        Date date = null;
        if (dateFormat == null) {
            dateFormat = "yyyyMM";
        }
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            date = format.parse(datadate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);     //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        calendar.add(Calendar.HOUR_OF_DAY, 23);
        calendar.add(Calendar.MINUTE, 59);
        calendar.add(Calendar.SECOND, 59);
        if (outFormat == null) {
            outFormat = "YYYY-MM-dd HH:mm:ss";
        }
        SimpleDateFormat fo = new SimpleDateFormat(outFormat);
        return fo.format(calendar.getTime());
    }

    /**
     * 获取指定时间所在月份的第一天的时间
     *
     * @param datadate
     * @param dateFormat
     * @param outFormat
     * @return
     * @throws Exception
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static String getFirstDay(String datadate, String dateFormat, String outFormat) {
        Date date = null;
        if (dateFormat == null) {
            dateFormat = "yyyyMM";
        }
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            date = format.parse(datadate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.MINUTE, 0);
        calendar.add(Calendar.SECOND, 0);
        if (outFormat == null) {
            outFormat = "YYYY-MM-dd HH:mm:ss";
        }
        SimpleDateFormat fo = new SimpleDateFormat(outFormat);
        return fo.format(calendar.getTime());
    }

    /**
     * 比较时间大小
     *
     * @param start
     * @param end
     * @param dateformat
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static boolean dateCompare(String start, String end, String dateformat) {
        if (dateformat == null) {
            dateformat = "HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(dateformat);
        try {
            Date sdate = format.parse(start);
            Date edate = format.parse(end);
            return sdate.after(edate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取当前时间的前/后numDays天后指定时间点的时间字符
     *
     * @param numDays
     * @param format
     * @param hour
     * @param minute
     * @param second
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static String getCurDayBeforDay(int numDays, String format, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - numDays);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        if (format == null) {
            format = "YYYY-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(cal.getTime());
    }

    /**
     * 验证是否合法的日期格式
     *
     * @param validatestr
     * @return
     * @author libo 工号
     * @see [类、类#方法、类#成员]
     */
    public static boolean validateTime(String validatestr) {
        if (StringUtils.isEmpty(validatestr)) return false;
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        return isMatch(rexp, validatestr);
    }

    /**
     * 生产合同编号
     *
     * @param maxCount
     * @return
     */
    public static String recountNew(int maxCount) {
        if (maxCount < 0) {
            return null;
        }
        // 20170731FXJT99999999
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(new Date());
        maxCount = maxCount + 1;
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(9);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(9);

        String countStr = str + "FXJT" + nf.format(maxCount);
        System.out.println("合同编号: " + countStr);
        return countStr;
    }

    /**
     * 判断时间与档期那时间比较 true 小于当前时间 false:大于当前时间
     * @param time
     * @return
     */
    public static Boolean compareTime(String time) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String nowtime = sdf.format(date);//当前时间

        String endtime = time;//到期时间
        try {
            if (sdf.parse(nowtime).getTime() > sdf.parse(endtime).getTime()) {//转成long类型比较
                return true;
            } else if (sdf.parse(nowtime).getTime() <= sdf.parse(endtime).getTime()) {
                System.out.println("当前时间小于等于到期时间");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
