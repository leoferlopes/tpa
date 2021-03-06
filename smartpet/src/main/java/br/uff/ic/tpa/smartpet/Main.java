package br.uff.ic.tpa.smartpet;

import br.uff.ic.tpa.smartpet.form.*;
import br.uff.ic.tpa.smartpet.model.*;
import br.uff.ic.tpa.smartpet.service.*;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.persistence.OneToMany;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author leonardo
 */
public class Main {

    public static void main(String[] args) {
        FramePrincipal principal = new FramePrincipal();
        principal.setFocusableWindowState(true);
        principal.setVisible(true);
    }

    public static void _main(String[] args) {
        ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

        ConsultaAppService consultaAppService = (ConsultaAppService) fabrica.getBean("consultaAppService");
        DonoAppService donoAppService = (DonoAppService) fabrica.getBean("donoAppService");
        EspecieAppService especieAppService = (EspecieAppService) fabrica.getBean("especieAppService");
        PacienteAppService pacienteAppService = (PacienteAppService) fabrica.getBean("pacienteAppService");
        VeterinarioAppService veterinarioAppService = (VeterinarioAppService) fabrica.getBean("veterinarioAppService");

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipClass(Class<?> c) {
                        return false;
                    }

                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getAnnotation(OneToMany.class) != null;
                    }
                })
                .serializeNulls()
                .create();
        try {
            List<Dono> list = donoAppService.recuperaDonos();
            System.out.println(gson.toJson(list));
            System.out.println("count: " + donoAppService.contaListaDeDonos());

            list = donoAppService.recuperaPaginaDeDonosPorNome(0, 1, "s");
            System.out.println(gson.toJson(list));
            System.out.println("count: " + donoAppService.contaListaDeDonosPorNome("s"));
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
