package cn.edu.cqut.weknow.dao.test;

import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import cn.edu.cqut.weknow.utils.RSAUtils;
import cn.edu.cqut.weknow.utils.Utils;

/*
 * 一些常用的不确定的java类的使用的测试程序通常放在本类中
 */
public class MainTest
{
	public static void main(String[] args)
	{
		// encodeAndDecodeStringByBase64();
		encodeStringByMD5();
//		encodeAndDecodeStringByRSA();
	}

	public static void dateFormat()
	{
		// 测试日期格式
		Date date = new Date();
		DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM");
		String dateS = dateFormatDate.format(date);
		System.out.println(dateS);
	}

	public static void getPositionByIp()
	{
		// 测试根据ip地址获取位置
		// String ip = "131.217.255.204";
		// String ip = "122.49.20.247";
		// String ip = "101.79.148.168";
		// String ip = "222.178.158.51";
		String ip = "101.180.209.168";
		String address = "";
		try
		{
			address = Utils.getAddresses("ip=" + ip, "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(address);
		// 输出结果为：广东省,广州市,越秀区
	}

	public static void encodeAndDecodeStringByBase64()
	{
		// 采用base64进行编码和解码
		String srcStr = "cn.edu.cqut.weknow";
		String encodedStr = Base64.getEncoder().encodeToString(srcStr.getBytes());
		System.out.println(encodedStr);
		byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
		String decodeStr = new String(decodedBytes);
		System.out.println(decodeStr);
	}

	public static void encodeStringByMD5()
	{
		// 采用MD5进行单向编码
		try
		{
			String srcStr = "admin";
			String encodedStr = Utils.encodeStringByMD5(srcStr);
			System.out.println(encodedStr.length() + ":" + encodedStr);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void encodeAndDecodeStringByRSA()
	{
		try
		{
			HashMap<String, Object> map = RSAUtils.getKeys();
			// 生成公钥和私钥
			RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
			RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");

			// 模
			String modulus = publicKey.getModulus().toString();
			// 公钥指数
			String public_exponent = publicKey.getPublicExponent().toString();
			// 私钥指数
			String private_exponent = privateKey.getPrivateExponent().toString();
			// 明文
			String ming = "http://www.cqut.edu.cn";
			System.out.println("明文："+ming);
			// 使用模和指数生成公钥和私钥
			RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);
			RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);
			// 加密后的密文
			String mi = RSAUtils.encryptByPublicKey(ming, pubKey);
			System.out.println("加密后："+mi);
			// 解密后的明文
			ming = RSAUtils.decryptByPrivateKey(mi, priKey);
			System.out.println("解密后："+ming);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
