package test;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @Author Hankin
 * @date 2020/3/6 5:11 下午
 * @Version 1.0
 * @Description:
 */
@Data
class Fu{
    private String name = "FU";

    public void say(){
        System.out.println(name);
    }
}

@Data
class Zi extends Fu{
    private String name = "ZI";
}
public class MyTest {

    public static void main(String[] args) {
        Zi zi = new Zi();
        System.out.println(JSON.toJSON(zi));
    }
}
