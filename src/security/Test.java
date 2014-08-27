
import com.tencent.framework.util.MD5Util;
import java.security.NoSuchAlgorithmException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(MD5Util.md5("admin"));
    }
    
}
