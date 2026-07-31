package br.com.ayshasakai.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeanWrapper;

public class Utils {

    //Método para copiar propriedades não nulas de um objeto para outro
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullProertiesNames(source));
        
        }
        
    
    //Método para obter os nomes das propriedades nulas de um objeto
    public static String[] getNullProertiesNames(Object source) {
        //Cria um BeanWrapper para o objeto de origem
        final BeanWrapper src = new BeanWrapperImpl(source);
        
        //Obtém os nomes das propriedades do objeto de origem
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        //Cria um conjunto para armazenar os nomes das propriedades nulas
        Set<String> emptyNames = new HashSet<>();

        //Itera sobre as propriedades e verifica se são nulas
        for(PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            //Se o valor da propriedade for nulo, adiciona o nome da propriedade ao conjunto
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        //Converte o conjunto de nomes de propriedades nulas em um array de strings
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
