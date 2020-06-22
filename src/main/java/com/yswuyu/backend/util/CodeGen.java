package com.yswuyu.backend.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author Hankin
 * @date 2019/11/16 9:45
 * @description:
 */
@Slf4j
@Component
public class CodeGen {

    public String genCode(Long userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String uId = null;
        if (userId == null) {
            uId = String.format("%03d", 100 + new Random().nextInt(900));
        } else {
            uId = splitUserId(userId);
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String dateTime = dateTimeFormatter.format(localDateTime);
        String seq = String.format("%03d", 100 + new Random().nextInt(900));
        return uId + dateTime + seq;
    }

    /**
     * @param userId
     * @return
     * @description <pre>
     * 截取三位长度的用户id
     * </pre>
     */
    private String splitUserId(Long userId) {
        String id = String.valueOf(userId);
        if (id.length() == 3) {
            return userId.toString();
        } else if (id.length() > 3) {
            return id.substring(id.length() - 3, id.length());
        } else {
            return String.format("%03d", userId);
        }
    }

//    public static void main(String[] args) throws Exception {
//        FileWriter writer=new FileWriter("/Volumes/Freestyle/data-5000-3.txt");
//        AtomicInteger count = new AtomicInteger(0);
//        Set set = new CopyOnWriteArraySet();
//        CodeGen codeGen = new CodeGen();
//        int treadNum = 10;
//        int codeNum = 500;
//        CountDownLatch countDownLatch = new CountDownLatch(treadNum);
//        for (int i = 0; i < treadNum; i++) {
//            new Thread("" + i) {
//                @Override
//                public void run() {
//                    for (int i = 0; i < codeNum; i++) {
//                        String orderCode = codeGen.genCode(null);
//                        set.add(orderCode);
//                        try {
//                            writer.write(orderCode+"\r\n");
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    countDownLatch.countDown();
//                }
//            }.start();
//        }
//        countDownLatch.await();
//        writer.close();
//        System.out.println("重复率:" + (treadNum * codeNum-set.size()) * 1.0 / (treadNum * codeNum));
//    }
}
