import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Pesel {

    public static  String losujPesel(){

        Random rand = new Random();

        int firstPart = rand.nextInt(78) + 22;
        int secondPart = rand.nextInt(12) + 1;
        int thirdPart = rand.nextInt(31) + 1;

        String pesel;

        pesel = String.valueOf(firstPart);
        if(secondPart<10)
            pesel+="0";
        pesel += String.valueOf(secondPart);
        if(thirdPart<10)
            pesel+="0";
        pesel += String.valueOf(thirdPart);
        int losowa;

        for (int i = 0; i <4;i++){
            if(i==3) {
                losowa = rand.nextInt(10) + 0;
                while (losowa%2!=0) {
                    losowa = rand.nextInt(10) + 0;
                }
                pesel += String.valueOf(losowa);
            }
            losowa = rand.nextInt(10) + 0;
            pesel += String.valueOf(losowa);
        }

        return pesel;
    }

    public static  int losujNumer(int x){

        Random rand = new Random();

        int rd = rand.nextInt(x) + 0;

        return rd;
    }
    public static void main(String[] args) throws IOException {

        String line;

        List <String> listaImion = new ArrayList <String>();
        List <String> listaNazwisk = new ArrayList <String>();

        int len;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("imie.txt"));

            line = bufferedReader.readLine();

            if (!line.equals("Jarema")) {
                len = line.length();
                if (line.charAt(len - 1) == 'a') {//System.out.println("Wczytane imię: " + line);
                    listaImion.add(line);
                }
            }

            while (line != null) {
                if (!line.equals("Jarema")) {
                    len = line.length();
                    if (line.charAt(len - 1) == 'a') {
                        //System.out.println("Wczytane imię: " + line);
                        listaImion.add(line);
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("nazwisko.txt"));

            line = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line);

            while (stringTokenizer.hasMoreTokens()) {
                stringTokenizer.nextElement();
                listaNazwisk.add(stringTokenizer.nextToken());
                //System.out.println(stringTokenizer.nextElement());
                break;
            }

            while (line != null) {


                stringTokenizer = new StringTokenizer(line);

                while (stringTokenizer.hasMoreTokens()) {
                    stringTokenizer.nextElement();
                    listaNazwisk.add(stringTokenizer.nextToken());
                    //System.out.println(stringTokenizer.nextElement());
                    break;
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**for (String temp : listaImion) {
            System.out.println(temp);
        }*/

        /**for (String temp : listaNazwisk) {
            System.out.println(temp);
        }*/

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj liczbę z zakresu [1-100]: ");
        int zakres = Integer.parseInt(bufferedReader.readLine());
        while (zakres > 100 || zakres < 1){
            System.out.println("Błędny przedział, sprobój ponownie: ");
            zakres = Integer.parseInt(bufferedReader.readLine());
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("szpieg.txt"));

            for (int i = 0; i < zakres; i++) {
                String rekord;
                rekord = listaImion.get(losujNumer(listaImion.size()));
                rekord += " ";
                rekord += listaNazwisk.get(losujNumer(listaNazwisk.size()));
                rekord += " ";
                rekord += losujPesel();
                System.out.println(rekord);
                bufferedWriter.write(rekord);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}
