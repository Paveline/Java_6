package main.java.com.bsu;

import java.util.ArrayList;
import java.util.List;

public class Query {
    List<Company> compByShortName(String str, List<Company> companies){
        List<Company> result = new ArrayList<>();

        for (Company item : companies) {
            if (item.getShortCompanyName().toLowerCase().equals(str.toLowerCase())) {
                result.add(item);
            }
        }

        return result;
    }

    List<Company> compByIndustry(String str, List<Company> companies){
        List<Company> result = new ArrayList<>();

        for (Company item : companies) {
            if (item.getIndustry().toLowerCase().equals(str.toLowerCase())) {
                result.add(item);
            }
        }

        return result;
    }

    List<Company> compByActivity(String str, List<Company> companies){
        List<Company> result = new ArrayList<>();

        for (Company item : companies) {
            if (item.getTypeOfActivity().toLowerCase().equals(str.toLowerCase())) {
                result.add(item);
            }
        }

        return result;
    }

    List<Company> compByDateOfFoundation(int start, int end, List<Company> companies){
        List<Company> result = new ArrayList<>();

        for (Company item : companies) {
            if (start <= item.getDateOfFoundation() && item.getDateOfFoundation() <= end) {
                result.add(item);
            }
        }

        return result;
    }

    List<Company> compByCountOfEmployes(int start, int end, List<Company> companies){
        List<Company> result = new ArrayList<>();

        for (Company item : companies) {
            if (start <= item.getCountOfEmployes() && item.getCountOfEmployes() <= end) {
                result.add(item);
            }
        }

        return result;
    }
}
