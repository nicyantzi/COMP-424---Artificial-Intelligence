import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by nic on 2016-01-28.
 */
public class Optimization {

    public static void main (String args[]){
        try {
            HillClimbing();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void HillClimbing() throws IOException {

        FileWriter writer = new FileWriter("Hill Climbing.csv");

        double startPoint;
        double stepSize;

        double xCurrent;
        double yCurrent;
        double xLeft;
        double yLeft;
        double xRight;
        double yRight;


        for(startPoint = 0; startPoint < 11; startPoint++){

            for(stepSize = 0.01; stepSize < 0.11; stepSize = stepSize + 0.01){

                xCurrent = startPoint;
                stepSize = Round(stepSize);
                int numberOfSteps=0;


                while (true){

                    numberOfSteps++;

                    yCurrent = Function(xCurrent);
                    yCurrent = Round(yCurrent);

                    xLeft = xCurrent - stepSize;
                    xLeft = Round(xLeft);
                    yLeft = Function(xLeft);
                    yLeft = Round(yLeft);

                    xRight = xCurrent + stepSize;
                    xRight = Round(xRight);
                    yRight = Function(xRight);
                    yRight = Round(yRight);

                    if(yLeft > yCurrent){
                        xCurrent = xLeft;
                    }
                    else if(yRight > yCurrent){
                        xCurrent = xRight;
                    }
                    else{
                        System.out.println(startPoint+"     "+stepSize+"         "+numberOfSteps+"        "+xCurrent+"            "+yCurrent);

                        writer.append(startPoint+","+stepSize+","+numberOfSteps+","+xCurrent+","+yCurrent+"\n");

                        break;
                    }
                }
            }
        }
        writer.flush();
        writer.close();

    }

    public static double Function(double x){
        double y = Math.sin((Math.pow(x, 2))/2)/Math.sqrt(x);
        return y;

    }

    public static double Round(double number){
        number = Math.round(number * 1000000);
        number = number/1000000;
        return number;
    }


}
