package com.quincysx.crypto;

import com.quincysx.crypto.bip32.ExtendedKey;
import com.quincysx.crypto.bip32.ValidationException;
import com.quincysx.crypto.bip39.MnemonicGenerator;
import com.quincysx.crypto.bip39.RandomSeed;
import com.quincysx.crypto.bip39.SeedCalculator;
import com.quincysx.crypto.bip39.WordCount;
import com.quincysx.crypto.bip39.wordlists.English;
import com.quincysx.crypto.bip44.AddressIndex;
import com.quincysx.crypto.bip44.BIP44;
import com.quincysx.crypto.bip44.CoinPairDerive;
import com.quincysx.crypto.bitcoin.BitCoinECKeyPair;
import com.quincysx.crypto.eip55.EthCheckAddress;
import com.quincysx.crypto.ethereum.EthECKeyPair;
import com.quincysx.crypto.exception.CoinNotFindException;
import com.quincysx.crypto.exception.NonSupportException;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author QuincySx
 * @date 2018/3/2 下午5:29
 */
public class Bip32UnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        final StringBuilder sb = new StringBuilder();

    }
    
    public List<String> buildMonericToList(String moneric) {
    	moneric = moneric.trim();
    	return Arrays.asList(moneric.split("\\s"))	;
    }
    
    @Test
    public void testGenerate() {
//    	ExtendedKey aNew = ExtendedKey.createNew();
//    	Key master1 = aNew.getMaster();
//    	master1.getRawPrivateKey();//获取十六进制私钥
//    	master1.getRawPublicKey();//获取十六进制压缩公钥
//    	master1.getRawPublicKey(false);//获取十六进制公钥 传入参数压缩或者不压缩

    	

//    	BitCoinECKeyPair parse = BitCoinECKeyPair.parse(master1, true);
//    	System.out.println(parse.getPrivateKey());
//    	System.out.println(parse.getPublicKey());
//    	System.out.println(parse.getAddress());
//    	System.out.println();
//    	EthECKeyPair parse1 = EthECKeyPair.parse(master1);            
//    	System.out.println(parse1.getPrivateKey());
//    	System.out.println(parse1.getPublicKey());
//    	System.out.println(parse1.getAddress());
    	
    	
    	//以下方法直接调用会报错
//    	System.out.println(master1.getPrivateKey()); //获取格式化的私钥
//    	System.out.println(master1.getPublicKey());//获取格式化的公钥
//    	System.out.println(master1.getAddress());//获取格式化的地址
//    	System.out.println(master1.getRawAddress());//获取十六进制格式的地址
    	
    	
//    	MnemonicGenerator mnemonicCode = new MnemonicGenerator(English.INSTANCE);
//    	//参数是助记词的个数
//    	byte[] random = RandomSeed.random(WordCount.TWELVE); 
    	
//    	(可以通过此代码使用助记词恢复随机数 byte[] bytes = mnemonicCode.toEntropy(mnemonicWordsInAList );)
    	String moneric = "model you fix play pair push price purse inmate edit you finish";
    	List<String> mnemonicWordsInAList =  buildMonericToList(moneric);
    	String password = "";
//    	System.out.println(mnemonicWordsInAList);
////    	2 随机创建助记词（恢复私钥不需要）
    	byte[] seed =  new SeedCalculator()
                .withWordsFromWordList(English.INSTANCE)
                .calculateSeed(mnemonicWordsInAList, password);
    	try {
    		
    		
    		
			ExtendedKey extendedKey = ExtendedKey.create(seed);
			
//			AddressIndex address = BIP44.m().purpose44()
//                    .coinType(CoinTypes.Bitcoin)
//                    .account(0)
//                    .external()
//                    .address(0);
//			L5QCeWdPJxMCnrDzhNQwWqw919Ha6A7ATCHUZaY9uzeFVdpmeSzJ
//			0231e9ae3fd1b5c733c84c8b0f9f7e667e1bffbc7db0503936bcb33b88e617d241
//			1E8rvEmedTPNc84nAhrA96pQ5SbkJXwHuE
			CoinPairDerive cpd = new CoinPairDerive(extendedKey);
			ECKeyPair parse1=null;
			try {
//				m(/\\d+'?){3}/[0,1]/\\d+'?
				//coinomi wallet bip44格式为:m/44'/60'/0'/0
				//imtoken metamsk  默认为m/44'/60'/0'/0/0 ,
				parse1 = cpd.derive(BIP44.parsePath("m/44'/60'/0'/0/0"));
//				parse1 = cpd.derive(address);
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Key master1 = extendedKey.getMaster();
//			EthECKeyPair parse1 = EthECKeyPair.parse(master1);            
	    	System.out.println(parse1.getPrivateKey());
	    	System.out.println(parse1.getPublicKey());
	    	System.out.println(parse1.getAddress());
	    	
	    	boolean check5 = EthCheckAddress.checksumAddress("0x228f9e307F1e909E875719EBf0319716A99e7e70");
	    	System.out.println(check5);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
    }
}
