package africa.wheel.wheelgame.config;

import africa.wheel.wheelgame.model.Citazioni;
import africa.wheel.wheelgame.model.CitazioniRarita;
import africa.wheel.wheelgame.repository.CitazioniRaritaRepository;
import africa.wheel.wheelgame.repository.CitazioniRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(CitazioniRaritaRepository raritaRepository,
                                   CitazioniRepository citazioniRepository) {
        return args -> {
            if (raritaRepository.count() == 0) {
                raritaRepository.saveAll(Arrays.asList(
                        CitazioniRarita.builder().nome("Comune").valore(3).build(),
                        CitazioniRarita.builder().nome("Epico").valore(2).build(),
                        CitazioniRarita.builder().nome("Leggendario").valore(1).build(),
                        CitazioniRarita.builder().nome("Mitico").valore(0).build(),
                        CitazioniRarita.builder().nome("HolyShit").valore(4).build()
                ));
            }

            if (citazioniRepository.count() == 0) {
                CitazioniRarita comune = raritaRepository.findAll().stream().filter(r -> r.getNome().equals("Comune")).findFirst().orElse(null);
                CitazioniRarita epico = raritaRepository.findAll().stream().filter(r -> r.getNome().equals("Epico")).findFirst().orElse(null);
                CitazioniRarita leggendario = raritaRepository.findAll().stream().filter(r -> r.getNome().equals("Leggendario")).findFirst().orElse(null);
                CitazioniRarita mitico = raritaRepository.findAll().stream().filter(r -> r.getNome().equals("Mitico")).findFirst().orElse(null);
                CitazioniRarita holyShit = raritaRepository.findAll().stream().filter(r -> r.getNome().equals("HolyShit")).findFirst().orElse(null);

                java.util.Map<Integer, CitazioniRarita> map = new java.util.HashMap<>();
                map.put(3, comune);
                map.put(2, epico);
                map.put(1, leggendario);
                map.put(0, mitico);
                map.put(4, holyShit);

                List<Citazioni> data = Arrays.asList(
                    new Citazioni(null, "Fatto?", map.get(3)),
                    new Citazioni(null, "La somma fa il totale", map.get(2)),
                    new Citazioni(null, "Non è bello ciò che è bello... ma che bello che bello che bello!", map.get(2)),
                    new Citazioni(null, "Prontoooo, C6?", map.get(3)),
                    new Citazioni(null, "T'ho beccato eeeh", map.get(2)),
                    new Citazioni(null, "Fatto push (right click -> source -> format -> mm)", map.get(3)),
                    new Citazioni(null, "Domani si consegna", map.get(3)),
                    new Citazioni(null, "Deploya in Test", map.get(2)),
                    new Citazioni(null, "Ma l'hai fatto il corso? Quello della sicurezza?", map.get(1)),
                    new Citazioni(null, "Vi siete caffettati?", map.get(2)),
                    new Citazioni(null, "Buongiorno, caffè?", map.get(3)),
                    new Citazioni(null, "Modifiche porduttori", map.get(3)),
                    new Citazioni(null, "Vai, pulla, funziona", map.get(3)),
                    new Citazioni(null, "pull su nomeProgetto", map.get(2)),
                    new Citazioni(null, "Come te la cavi col css? buongiorno", map.get(1)),
                    new Citazioni(null, "non ho capito la logica", map.get(1)),
                    new Citazioni(null, "Bene", map.get(3)),
                    new Citazioni(null, "TUTTI AL MAREEEEE", map.get(1)),
                    new Citazioni(null, "Ma perché non rispondi?", map.get(3)),
                    new Citazioni(null, "Quanta la peraaa", map.get(2)),
                    new Citazioni(null, "Hai letto la mail? (Non è stata ancora inviata)", map.get(2)),
                    new Citazioni(null, "Ritornando a bomba", map.get(3)),
                    new Citazioni(null, "Sfogliatelo, prenditelo", map.get(3)),
                    new Citazioni(null, "E che il car sia con te!", map.get(1)),
                    new Citazioni(null, "AEO che non sono le vocali, ma è il progetto", map.get(2)),
                    new Citazioni(null, "Stamattina mi si è bloccato il water, buongiorno", map.get(0)),
                    new Citazioni(null, "Qualsiasi cosa ti deve dire\nbuongiorno", map.get(3)),
                    new Citazioni(null, "Fai il test in test", map.get(3)),
                    new Citazioni(null, "È una mia supposta", map.get(3)),
                    new Citazioni(null, "Lo fa Remo", map.get(2)),
                    new Citazioni(null, "La campania è come un virus", map.get(2)),
                    new Citazioni(null, "La sera leoni, la mattina... Eheh", map.get(1)),
                    new Citazioni(null, "qualcosa è stato scancellato", map.get(3)),
                    new Citazioni(null, "Click e click e click pare che non scoppia", map.get(0)),
                    new Citazioni(null, "Poi ti passo tutto il paccotto", map.get(2)),
                    new Citazioni(null, "Chiedete e vi sarà dato(parzialmente)", map.get(3)),
                    new Citazioni(null, "Che rispondo? Io non ho letto il documento", map.get(1)),
                    new Citazioni(null, "Azione qualsiasi Taaac", map.get(3)),
                    new Citazioni(null, "Madreh", map.get(0)),
                    new Citazioni(null, "Più meglio assai", map.get(2)),
                    new Citazioni(null, "Le librerie condivise mi fanno girare le palle", map.get(2)),
                    new Citazioni(null, "Altro giro, altra corsa (riavvia server)", map.get(3)),
                    new Citazioni(null, "Uccidi i Jhava", map.get(3)),
                    new Citazioni(null, "Grazie della giuda", map.get(3)),
                    new Citazioni(null, "Eddai che palleeeeeee", map.get(3)),
                    new Citazioni(null, "Che coglioni AO", map.get(2)),
                    new Citazioni(null, "bingo sarebbe la tombola?", map.get(3)),
                    new Citazioni(null, "Sono tornato dalle vacanze devo riprendere le coordinate.", map.get(3)),
                    new Citazioni(null, "Hai letto?", map.get(3)),
                    new Citazioni(null, "@Tipinga Habemus la select!", map.get(2)),
                    new Citazioni(null, "Per fare quello che dobbiamo fare, che cosa dobbiamo fare?", map.get(2)),
                    new Citazioni(null, "Ci 6? Ronfi Ronfi.", map.get(1)),
                    new Citazioni(null, "TODO exolab da risprintare", map.get(3)),
                    new Citazioni(null, "Gli dicono qualcosa al telefono... risposta YEAH", map.get(3)),
                    new Citazioni(null, "Fintanto che è dura fa verdura", map.get(2)),
                    new Citazioni(null, "Lo spikkespan", map.get(2)),
                    new Citazioni(null, "Tra il dire ed il fare c'è di mezzo l'oceano", map.get(1)),
                    new Citazioni(null, "Tutto il mondo è paese", map.get(3)),
                    new Citazioni(null, "L'uomo del monte ha detto si!", map.get(3)),
                    new Citazioni(null, "Si ok", map.get(3)),
                    new Citazioni(null, "Ma come funziona/non funziona? Non é possibile", map.get(3)),
                    new Citazioni(null, "Sto corso mi sta sulle palle", map.get(0)),
                    new Citazioni(null, "Carta manent scripta volant", map.get(0)),
                    new Citazioni(null, "L'affare si ingrossa", map.get(2)),
                    new Citazioni(null, "Se funziona quanto basta non toccare che si guasta", map.get(3)),
                    new Citazioni(null, "No perche praticamente", map.get(4))
                );
                citazioniRepository.saveAll(data);
            }
        };
    }
}
