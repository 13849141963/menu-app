package com.zy.cn.util;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
/*****
 * 生成字符串工具类
 */
public class RandomUtil {
	
	 public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	    public static final String NUMBERCHAR = "0123456789";  

	    /** 
	     * 返回一个定长的随机字符串(只包含大小写字母、数字) 
	     *  
	     * @param length 
	     *            随机字符串长度 
	     * @return 随机字符串 
	     */  
	    public static String generateString(int length) {  
	        StringBuffer sb = new StringBuffer();  
	        Random random = new Random();  
	        for (int i = 0; i < length; i++) {  
	            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
	        }  
	        return sb.toString();  
	    }  

	    /** 
	     * 返回一个定长的随机纯字母字符串(只包含大小写字母) 
	     *  
	     * @param length 
	     *            随机字符串长度 
	     * @return 随机字符串 
	     */  
	    public static String generateMixString(int length) {  
	        StringBuffer sb = new StringBuffer();  
	        Random random = new Random();  
	        for (int i = 0; i < length; i++) {  
	            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));  
	        }  
	        return sb.toString();  
	    }  

	    /** 
	     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母) 
	     *  
	     * @param length 
	     *            随机字符串长度 
	     * @return 随机字符串 
	     */  
	    public static String generateLowerString(int length) {  
	        return generateMixString(length).toLowerCase();  
	    }  

	    /** 
	     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母) 
	     *  
	     * @param length 
	     *            随机字符串长度 
	     * @return 随机字符串 
	     */  
	    public static String generateUpperString(int length) {  
	        return generateMixString(length).toUpperCase();  
	    }  

	    /** 
	     * 生成一个定长的纯0字符串 
	     *  
	     * @param length 
	     *            字符串长度 
	     * @return 纯0字符串 
	     */  
	    public static String generateZeroString(int length) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < length; i++) {  
	            sb.append('0');  
	        }  
	        return sb.toString();  
	    }  

	    /** 
	     * 根据数字生成一个定长的字符串，长度不够前面补0 
	     *  
	     * @param num 
	     *            数字 
	     * @param fixdlenth 
	     *            字符串长度 
	     * @return 定长的字符串 
	     */  
	    public static String toFixdLengthString(long num, int fixdlenth) {  
	        StringBuffer sb = new StringBuffer();  
	        String strNum = String.valueOf(num);  
	        if (fixdlenth - strNum.length() >= 0) {  
	            sb.append(generateZeroString(fixdlenth - strNum.length()));  
	        } else {  
	            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth  
	                    + "的字符串发生异常！");  
	        }  
	        sb.append(strNum);  
	        return sb.toString();  
	    }  

	    /** 
	     * 每次生成的len位数都不相同 
	     *  
	     * @param param 
	     * @return 定长的数字 
	     */  
	    public static int getNotSimple(int[] param, int len) {  
	        Random rand = new Random();  
	        for (int i = param.length; i > 1; i--) {  
	            int index = rand.nextInt(i);  
	            int tmp = param[index];  
	            param[index] = param[i - 1];  
	            param[i - 1] = tmp;  
	        }  
	        int result = 0;  
	        for (int i = 0; i < len; i++) {  
	            result = result * 10 + param[i];  
	        }  
	        return result;  
	    }  

	    public static void main(String[] args) {  
	        System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + generateString(10));  
	        System.out  
	                .println("返回一个定长的随机纯字母字符串(只包含大小写字母):" + generateMixString(10));  
	        System.out.println("返回一个定长的随机纯大写字母字符串(只包含大小写字母):"  
	                + generateLowerString(10));  
	        System.out.println("返回一个定长的随机纯小写字母字符串(只包含大小写字母):"  
	                + generateUpperString(10));  
	        System.out.println("生成一个定长的纯0字符串:" + generateZeroString(10));  
	        System.out.println("根据数字生成一个定长的字符串，长度不够前面补0:"  
	                + toFixdLengthString(123, 10));  
	        int[] in = { 1, 2, 3, 4, 5, 6, 7 };  
	        System.out.println("每次生成的len位数都不相同:" + getNotSimple(in, 3));
	        
	        
	        
	        long randomLong = randomLong(9);
	        System.out.println("参数"+randomLong);
	    }  
	    
	    private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };  
	    
	    /** 
	     * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位) 
	     *  
	     * @param digit 
	     *            用户指定随机数据的位数 
	     */  
	    public static long randomLong(int digit) {  
	        if (digit >= 19 || digit <= 0)  
	            throw new IllegalArgumentException("digit should between 1 and 18(1<=digit<=18)");  
	        String s = RandomStringUtils.randomNumeric(digit - 1);  
	        return Long.parseLong(getPrefix() + s);  
	    }  
	  
	    /** 
	     * 随机产生在指定位数之间的long型数据,位数包括两边的值,minDigit<=maxDigit 
	     *  
	     * @param minDigit 
	     *            用户指定随机数据的最小位数 minDigit>=1 
	     * @param maxDigit 
	     *            用户指定随机数据的最大位数 maxDigit<=18 
	     */  
	    public static long randomLong(int minDigit, int maxDigit)  {  
	        if (minDigit > maxDigit) {  
	            throw new IllegalArgumentException("minDigit > maxDigit");  
	        }  
	        if (minDigit <= 0 || maxDigit >= 19) {  
	            throw new IllegalArgumentException("minDigit <=0 || maxDigit>=19");  
	        }  
	        return randomLong(minDigit + getDigit(maxDigit - minDigit));  
	    }  
	  
	    private static int getDigit(int max) {  
	        return RandomUtils.nextInt(max + 1);  
	    }  
	  
	    /** 
	     * 保证第一位不是零 
	     *  
	     * @return 
	     */  
	    private static String getPrefix() {  
	        return prefix[RandomUtils.nextInt(9)] + "";  
	    }  

}
