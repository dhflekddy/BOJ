import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 한번에 맞긴 했지만 문제 난이도를 생각할때 시간이 너무 오래 걸렸다. 뭔가 solution함수가 분기문이 많아 지저분한 느낌이 든다. 다른 코드는 어떤지 살펴보자
 */
public class Main{
    static int solution1(int asset, int[]price){
        int myStock=0;
        for(var p:price){
            if(asset/p>0) {
                int num= (asset / p);
                asset-=(num*p);
                myStock+=num;
            }
        }
        return asset+myStock*price[13];
    }
    static int solution2(int asset, int[]price){
        int myStock=0;
        for(int i=3;i<14;i++){
            boolean bDay=false;
            if(asset/price[i]>0) {//사는 날
                int j=i-1;
                while (j >= i - 3) {
                    if (price[j] > price[j + 1]) {
                        if (j == i - 3 && asset / price[i] > 0) {
                            int num = (asset / price[i]);
                            asset -= (num * price[i]);
                            myStock += num;
                            bDay = true;
                        }
                        j -= 1;
                    } else
                        break;
                }
            }
            if(!bDay && myStock>0){//파는날
                int j=i-1;
                while(j>=i-3){
                    if(price[j]<price[j+1]){
                        if(j==i-3){
                            asset+=(myStock*price[i]);
                            myStock=0;
                        }
                        j-=1;
                    }
                    else
                        break;
                }
            }
        }
        return myStock*price[13]+asset;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int asset=Integer.parseInt(br.readLine());
        int[]price= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result1=solution1(asset, price);
        int result2=solution2(asset, price);

        if (result1 > result2) {
            System.out.println("BNP");
        } else {
            if (result1 == result2) {
                System.out.println("SAMESAME");
            } else {
                System.out.println("TIMING");
            }
        }
    }
}