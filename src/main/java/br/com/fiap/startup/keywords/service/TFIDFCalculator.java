package br.com.fiap.startup.keywords.service;

import java.util.*;

class TFIDFCalculator {
    public double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }

    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);
    }

    public static List<String> getKeywords(String text) {
        List<String> stopWords = Arrays.asList(getStopWords());
        List<List<String>> documents = getDocuments(text);
        SortedSet<Map.Entry<String, Double>> tfIDF = getTfIDF(stopWords, documents, documents.get(0));
        List<String> keywords = new ArrayList<>();
        int percentage = (int) (documents.get(0).size()*0.2);
        int i = 0;
        for (Map.Entry<String, Double> stringDoubleEntry : tfIDF) {
            if (i<percentage)
                keywords.add(stringDoubleEntry.getKey());
            else break;
                i++;
        }
        return keywords;
    }


    public static SortedSet<Map.Entry<String, Double>> getTfIDF(List<String> stopWords, List<List<String>> documents,
                                                                List<String> document) {
        TreeMap<String, Double> treeMap = new TreeMap<>();
        TFIDFCalculator calculator = new TFIDFCalculator();
        for (String word : document) {
            double tfidf = calculator.tfIdf(document, documents, word);
            treeMap.put(word, tfidf);
        }
        treeMap.keySet().removeAll(stopWords);
        return entriesSortedByValues(treeMap);
    }

    private static List<List<String>> getDocuments(String text) {
        List<List<String>> documents = new ArrayList<>();
        List<String> paragraphs = Arrays.asList(text.split("\\. "));
        for (String paragraph : paragraphs) {
            documents.add(Arrays.asList(paragraph.split(" ")));
        }
        return documents;
    }

    private static String[] getStopWords() {
        return new String[]{"de", "a",
                "o",
                "que",
                "e",
                "do",
                "da",
                "em",
                "um",
                "para",
                "??",
                "com",
                "n??o",
                "uma",
                "os",
                "no",
                "se",
                "na",
                "por",
                "mais",
                "as",
                "dos",
                "como",
                "mas",
                "foi",
                "ao",
                "ele",
                "das",
                "tem",
                "??",
                "seu",
                "sua",
                "ou",
                "ser",
                "quando",
                "muito",
                "h??",
                "nos",
                "j??",
                "est??",
                "eu",
                "tamb??m",
                "s??",
                "pelo",
                "pela",
                "at??",
                "isso",
                "ela",
                "entre",
                "era",
                "depois",
                "sem",
                "mesmo",
                "aos",
                "ter",
                "seus",
                "quem",
                "nas",
                "me",
                "esse",
                "eles",
                "est??o",
                "voc??",
                "tinha",
                "foram",
                "essa",
                "num",
                "nem",
                "suas",
                "meu",
                "??s",
                "minha",
                "t??m",
                "numa",
                "pelos",
                "elas",
                "havia",
                "seja",
                "qual",
                "ser??",
                "n??s",
                "tenho",
                "lhe",
                "deles",
                "essas",
                "esses",
                "pelas",
                "este",
                "fosse",
                "dele",
                "tu",
                "te",
                "voc??s",
                "vos",
                "lhes",
                "meus",
                "minhas",
                "teu",
                "tua",
                "teus",
                "tuas",
                "nosso",
                "nossa",
                "nossos",
                "nossas",
                "dela",
                "delas",
                "esta",
                "estes",
                "estas",
                "aquele",
                "aquela",
                "aqueles",
                "aquelas",
                "isto",
                "aquilo",
                "estou",
                "est??",
                "estamos",
                "est??o",
                "estive",
                "esteve",
                "estivemos",
                "estiveram",
                "estava",
                "est??vamos",
                "estavam",
                "estivera",
                "estiv??ramos",
                "esteja",
                "estejamos",
                "estejam",
                "estivesse",
                "estiv??ssemos",
                "estivessem",
                "estiver",
                "estivermos",
                "estiverem",
                "hei",
                "h??",
                "havemos",
                "h??o",
                "houve",
                "houvemos",
                "houveram",
                "houvera",
                "houv??ramos",
                "haja",
                "hajamos",
                "hajam",
                "houvesse",
                "houv??ssemos",
                "houvessem",
                "houver",
                "houvermos",
                "houverem",
                "houverei",
                "houver??",
                "houveremos",
                "houver??o",
                "houveria",
                "houver??amos",
                "houveriam",
                "sou",
                "somos",
                "s??o",
                "era",
                "??ramos",
                "eram",
                "fui",
                "foi",
                "fomos",
                "foram",
                "fora",
                "f??ramos",
                "seja",
                "sejamos",
                "sejam",
                "fosse",
                "f??ssemos",
                "fossem",
                "for",
                "formos",
                "forem",
                "serei",
                "ser??",
                "seremos",
                "ser??o",
                "seria",
                "ser??amos",
                "seriam",
                "tenho",
                "tem",
                "temos",
                "t??m",
                "tinha",
                "t??nhamos",
                "tinham",
                "tive",
                "teve",
                "tivemos",
                "tiveram",
                "tivera",
                "tiv??ramos",
                "tenha",
                "tenhamos",
                "tenham",
                "tivesse",
                "tiv??ssemos",
                "tivessem",
                "tiver",
                "tivermos",
                "tiverem",
                "terei",
                "ter??",
                "teremos",
                "ter??o",
                "teria",
                "ter??amos",
                "teriam",
                "!",
                "#",
                "$",
                "%",
                "&",
                "'",
                "(",
                ")",
                "*",
                "+",
                ",",
                "-",
                ".",
                "/",
                ":",
                ";",
                "<",
                "=",
                ">",
                "?",
                "@",
                "\\",
                "^",
                "_",
                "`",
                "{",
                "|",
                "}",
                "~"
        };
    }

    static <K, V extends Comparable<? super V>>
    SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<>(
                (e1, e2) -> {
                    int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1;
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

}