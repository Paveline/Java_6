package main.java.com.bsu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static List<Company> companies = new ArrayList<>();
    static List<String> requestsList = new ArrayList<>();
    static Query Tasks = new Query();
    static List<Company> result = new ArrayList<>();
    static int numberOfRequestFile = 1;

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("input.txt"); FileReader requestReader = new FileReader("requests.txt");
             Scanner scanRequest = new Scanner(requestReader); Scanner scan = new Scanner(reader); Scanner scanIn = new Scanner(System.in)) {

            while (scan.hasNextLine()) {
                pushToCompanies(scan.nextLine());
            }

            getTime();

            scanRequestsFromFile(scanRequest);

            for (String request : requestsList)
                parsingRequest(request, companies);

            menu(scanIn);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static public void menu(Scanner in) throws Exception {
        System.out.println("Введите номер одной из следующх задач");
        System.out.println("1. Найти компанию по краткому наименованию.");
        System.out.println("2. Выбрать компании по отрасли. ");
        System.out.println("3. Выбрать компании по виду деятельности.");
        System.out.println("4. Выбрать компании по дате основания в определенном промежутке (с и по).");
        System.out.println("5. Выбрать компании по численности сотрудников в определенном промежутке (с и по).");
        System.out.println("0. Завершить программу");

        int start;
        int end;

        int task = in.nextInt();
        String str;

        switch (task) {
            case 0:
                System.exit(0);

            case 1:
                System.out.println("Введите краткое наименование компании");
                str = in.next();
                result = Tasks.compByShortName(str, companies);

                for (Company item : result) {
                    System.out.println(item.getCompanyName());
                    writeInfoToFile("Краткое наименование - " + str + ": " + item.getCompanyName());
                }

                break;
            case 2:
                System.out.println("Введите название отрасли");
                str = in.next();
                result = Tasks.compByIndustry(str, companies);

                for (Company item : result) {
                    System.out.println(item.getCompanyName());
                    writeInfoToFile("Название отрасли - " + str + ": " + item.getCompanyName());
                }

                break;
            case 3:
                System.out.println("Введите вид деятельности компании");
                str = in.next();
                result = Tasks.compByActivity(str, companies);

                for (Company item : result) {
                    System.out.println(item.getCompanyName());
                    writeInfoToFile("Вид деятельности - " + str + ": " + item.getCompanyName());
                }

                break;
            case 4:
                System.out.println("Введите период основания компании");
                start = Integer.parseInt(in.next());
                end = Integer.parseInt(in.next());
                result = Tasks.compByDateOfFoundation(start, end, companies);

                for (Company item : result) {
                    System.out.println(item.getCompanyName());
                    writeInfoToFile("Период основания - " + start + "/" + end + ": " + item.getCompanyName());
                }

                break;

            case 5:
                System.out.println("Введите интервал количества сотрудников компании");
                start = Integer.parseInt(in.next());
                end = Integer.parseInt(in.next());
                result = Tasks.compByCountOfEmployes(start, end, companies);

                for (Company item : result) {
                    System.out.println(item.getCompanyName());
                    writeInfoToFile("Количество сотрудников - " + start + "/" + end + ": " + item.getCompanyName());
                }

                break;
            default:
                System.out.println("Такой команды не найдено, попробуйте еще раз");
                break;
        }

        menu(in);

    }

    public static void parsingRequest(String request, List<Company> companies) throws IOException {
        String[] words = request.toLowerCase().split("(=[ ]*\".*\" )|(=[ ]*\".*\"$)|[ ]+");
        Pattern pattern = Pattern.compile("\".*?\"");
        Matcher matcher = pattern.matcher(request);
        if (words.length >= 4 && words[0].equals(Requests.SELECT) && words[1].equals("*")
                && words[2].equals(Requests.FROM)) {
            List<Company> result;
            String fileName = "";

            if (words[3].equals(Requests.TABLE_NAME)) {
                if (words[4].equals(Requests.WHERE)) {
                    for (int i = 5; i < words.length; ++i) {
                        switch (words[i]) {
                            case Requests.SHORTNAME:
                                if (!matcher.find())
                                    throw new IOException("Неверный формат команды, проверте запрос еще раз");
                                result = Tasks.compByShortName(request.substring(matcher.start() + 1, matcher.end() - 1), companies);

                                fileName = "request" + numberOfRequestFile + ".txt";
                                for (Company item : result)
                                    requestWriter(item.toString(), fileName);
                                numberOfRequestFile++;
                                break;
                            case Requests.KIND_OF_ACTIVITY:
                                if (!matcher.find())
                                    throw new IOException("Неверный формат команды, проверте запрос еще раз");
                                result = Tasks.compByActivity(request.substring(matcher.start() + 1, matcher.end() - 1), companies);

                                fileName = "request" + numberOfRequestFile + ".txt";
                                for (Company item : result)
                                    requestWriter(item.toString(), fileName);
                                numberOfRequestFile++;
                                break;
                            case Requests.EMPLOYEE_NUMBER:
                                if (words[++i].equals(Requests.BETWEEN) && words[i + 2].equals(Requests.AND)) {
                                    int minNumber = Integer.parseInt(words[++i]);
                                    int maxNumber = Integer.parseInt(words[i + 2]);
                                    i = i + 2;
                                    result = Tasks.compByCountOfEmployes(minNumber, maxNumber, companies);

                                    fileName = "request" + numberOfRequestFile + ".txt";
                                    for (Company item : result)
                                        requestWriter(item.toString(), fileName);
                                    numberOfRequestFile++;
                                } else
                                    throw new IOException("Неверный формат команды, проверте запрос еще раз");
                                break;
                            default:
                                throw new IOException("Неверный формат команды, проверте запрос еще раз");
                        }

                    }
                }
            }
        }
    }

    static void scanRequestsFromFile(Scanner scanRequest) {
        while (scanRequest.hasNextLine()) {
            requestsList.add(scanRequest.nextLine());
        }
    }

    static void requestWriter(String str, String file) throws IOException {
        try (FileWriter writer = new FileWriter(file, true)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            writer.write(dateFormat.format(date) + "\n");
            writer.write(str + "\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static public void getTime() throws Exception {
        try (FileWriter writer = new FileWriter("logfile.txt", true)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            writer.write("\n" + dateFormat.format(date) + "\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    static void writeInfoToFile(String str) throws Exception {
        try (FileWriter writer = new FileWriter("logfile.txt", true)) {
            writer.write(str + "\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    static public void pushToCompanies(String str) {
        String[] arr = str.split(";");

        Company compInfo = new Company(arr);

        companies.add(compInfo);
    }

}
