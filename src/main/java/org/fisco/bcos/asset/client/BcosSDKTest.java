package org.fisco.bcos.asset.client;

import org.fisco.bcos.asset.contract.HelloWorld;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ResourceUtils;

public class BcosSDKTest
{
    @Test
    public void testClient() throws ConfigException, ContractException {
        // 获取配置文件路径
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 初始化BcosSDK
        BcosSDK sdk = context.getBean(BcosSDK.class);
        // 为群组1初始化client
        Client client = sdk.getClient(Integer.valueOf(1));

        // 获取群组1的块高
        BlockNumber blockNumber = client.getBlockNumber();

        // 使用默认账号(随机创建一个新的)
        CryptoKeyPair cryptoKeyPair =client.getCryptoSuite().getCryptoKeyPair();

        /*创建账号*/
//        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
//        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair();
//        cryptoKeyPair.storeKeyPairWithPemFormat();

        /* 实例化合约 */
        HelloWorld helloWorld = HelloWorld.load(
                "0x3e35b2429cc5e488b1af0d809b830f7d6de8a9ae",
                client, cryptoKeyPair);
        String getValue = helloWorld.get();   //调用合约的get方法
        System.out.println(getValue);

        /* 获取交易回执 */
//        TransactionReceipt receipt = helloWorld.set("Hello,WontonData!");
//        System.out.println(receipt.getContractAddress());
//        System.out.printf(helloWorld.get());

        /* 向群组1部署HelloWorld合约 */
//        HelloWorld helloWorld = HelloWorld.deploy(client, cryptoKeyPair);

        /* 调用HelloWorld合约的get接口 */
//        String getValue = helloWorld.get();

        /* 调用HelloWorld合约的set接口 */
//        TransactionReceipt receipt = helloWorld.set("Hello, fisco");
    }
}
