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
                "é",
                "com",
                "não",
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
                "à",
                "seu",
                "sua",
                "ou",
                "ser",
                "quando",
                "muito",
                "há",
                "nos",
                "já",
                "está",
                "eu",
                "também",
                "só",
                "pelo",
                "pela",
                "até",
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
                "estão",
                "você",
                "tinha",
                "foram",
                "essa",
                "num",
                "nem",
                "suas",
                "meu",
                "às",
                "minha",
                "têm",
                "numa",
                "pelos",
                "elas",
                "havia",
                "seja",
                "qual",
                "será",
                "nós",
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
                "vocês",
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
                "está",
                "estamos",
                "estão",
                "estive",
                "esteve",
                "estivemos",
                "estiveram",
                "estava",
                "estávamos",
                "estavam",
                "estivera",
                "estivéramos",
                "esteja",
                "estejamos",
                "estejam",
                "estivesse",
                "estivéssemos",
                "estivessem",
                "estiver",
                "estivermos",
                "estiverem",
                "hei",
                "há",
                "havemos",
                "hão",
                "houve",
                "houvemos",
                "houveram",
                "houvera",
                "houvéramos",
                "haja",
                "hajamos",
                "hajam",
                "houvesse",
                "houvéssemos",
                "houvessem",
                "houver",
                "houvermos",
                "houverem",
                "houverei",
                "houverá",
                "houveremos",
                "houverão",
                "houveria",
                "houveríamos",
                "houveriam",
                "sou",
                "somos",
                "são",
                "era",
                "éramos",
                "eram",
                "fui",
                "foi",
                "fomos",
                "foram",
                "fora",
                "fôramos",
                "seja",
                "sejamos",
                "sejam",
                "fosse",
                "fôssemos",
                "fossem",
                "for",
                "formos",
                "forem",
                "serei",
                "será",
                "seremos",
                "serão",
                "seria",
                "seríamos",
                "seriam",
                "tenho",
                "tem",
                "temos",
                "tém",
                "tinha",
                "tínhamos",
                "tinham",
                "tive",
                "teve",
                "tivemos",
                "tiveram",
                "tivera",
                "tivéramos",
                "tenha",
                "tenhamos",
                "tenham",
                "tivesse",
                "tivéssemos",
                "tivessem",
                "tiver",
                "tivermos",
                "tiverem",
                "terei",
                "terá",
                "teremos",
                "terão",
                "teria",
                "teríamos",
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