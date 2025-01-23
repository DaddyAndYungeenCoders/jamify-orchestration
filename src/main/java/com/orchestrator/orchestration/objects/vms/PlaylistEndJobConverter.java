package com.orchestrator.orchestration.objects.vms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlaylistEndJobConverter {

    /**
     * Convertit une chaîne JSON en objet PlaylistEndJobVM manuellement.
     *
     * @param json la chaîne JSON à convertir
     * @return l'objet PlaylistEndJobVM correspondant
     * @throws IllegalArgumentException si la conversion échoue
     */
    public static PlaylistEndJobVM fromJson(String json) {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException("La chaîne JSON ne peut pas être nulle ou vide.");
        }

        try {
            // Supprimer les espaces inutiles
            json = json.trim();

            // Extraire l'ID
            String id = extractStringValue(json, "id");

            // Extraire le userId
            String userId = extractStringValue(json, "userId");

            // Extraire le bloc "data"
            String dataBlock = extractBlock(json, "data");

            // Extraire les musics
            Set<Long> musics = extractIntegerList(dataBlock, "musics").stream().map(Long::valueOf).collect(Collectors.toSet());

            // Extraire le name
            String name = extractStringValue(dataBlock, "name");

            // Extraire la description
            String description = extractStringValue(dataBlock, "description");

            // Construire l'objet PlaylistEndJobPayloadVM
            PlaylistEndJobPayloadVM payload = new PlaylistEndJobPayloadVM(musics, name, description);

            // Construire l'objet PlaylistEndJobVM
            UUID uuid = UUID.fromString(id);
            return new PlaylistEndJobVM(uuid, userId, payload);

        } catch (Exception e) {
            throw new IllegalArgumentException("Erreur lors de la conversion du JSON en PlaylistEndJobVM", e);
        }
    }

    /**
     * Extrait la valeur d'une clé de type String dans un JSON simple.
     *
     * @param json la chaîne JSON
     * @param key  la clé dont on veut la valeur
     * @return la valeur associée à la clé
     */
    private static String extractStringValue(String json, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) {
            throw new IllegalArgumentException("Clé \"" + key + "\" non trouvée dans le JSON.");
        }

        int colonIndex = json.indexOf(":", keyIndex);
        int startQuote = json.indexOf("\"", colonIndex + 1);
        int endQuote = json.indexOf("\"", startQuote + 1);

        if (startQuote == -1 || endQuote == -1) {
            throw new IllegalArgumentException("Valeur pour la clé \"" + key + "\" mal formatée.");
        }

        return json.substring(startQuote + 1, endQuote);
    }

    /**
     * Extrait un bloc JSON associé à une clé.
     *
     * @param json la chaîne JSON
     * @param key  la clé dont on veut le bloc
     * @return le bloc JSON sous forme de chaîne
     */
    private static String extractBlock(String json, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) {
            throw new IllegalArgumentException("Clé \"" + key + "\" non trouvée dans le JSON.");
        }

        int colonIndex = json.indexOf(":", keyIndex);
        int startBrace = json.indexOf("{", colonIndex + 1);
        int endBrace = findMatchingBrace(json, startBrace);

        if (startBrace == -1 || endBrace == -1) {
            throw new IllegalArgumentException("Bloc pour la clé \"" + key + "\" mal formaté.");
        }

        return json.substring(startBrace, endBrace + 1);
    }

    /**
     * Trouve l'index de la accolade fermante correspondante.
     *
     * @param json       la chaîne JSON
     * @param startIndex l'index de la première accolade ouvrante
     * @return l'index de l'accolade fermante correspondante
     */
    private static int findMatchingBrace(String json, int startIndex) {
        int braceCount = 0;
        for (int i = startIndex; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                braceCount++;
            } else if (c == '}') {
                braceCount--;
                if (braceCount == 0) {
                    return i;
                }
            }
        }
        return -1; // Aucun match trouvé
    }

    /**
     * Extrait une liste d'entiers à partir d'un bloc JSON.
     *
     * @param jsonBlock le bloc JSON
     * @param key       la clé dont on veut la liste
     * @return une liste d'entiers
     */
    private static List<Integer> extractIntegerList(String jsonBlock, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = jsonBlock.indexOf(searchKey);
        if (keyIndex == -1) {
            throw new IllegalArgumentException("Clé \"" + key + "\" non trouvée dans le bloc JSON.");
        }

        int colonIndex = jsonBlock.indexOf(":", keyIndex);
        int startBracket = jsonBlock.indexOf("[", colonIndex + 1);
        int endBracket = jsonBlock.indexOf("]", startBracket + 1);

        if (startBracket == -1 || endBracket == -1) {
            throw new IllegalArgumentException("Liste pour la clé \"" + key + "\" mal formatée.");
        }

        String listContent = jsonBlock.substring(startBracket + 1, endBracket).trim();
        List<Integer> result = new ArrayList<>();

        if (!listContent.isEmpty()) {
            String[] items = listContent.split(",");
            for (String item : items) {
                try {
                    result.add(Integer.parseInt(item.trim()));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Valeur non entière dans la liste \"" + key + "\": " + item);
                }
            }
        }

        return result;
    }
}
