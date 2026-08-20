package com.example.Shetkari_Mitra;

import java.util.ArrayList;
import java.util.List;

public class CreatureDataProvider {

    public static List<HarmfulCreature> getCreatures() {
        List<HarmfulCreature> list = new ArrayList<>();

        // 1. Indian Red Scorpion
        list.add(new HarmfulCreature(
                "Indian Red Scorpion",
                "तांबडा विंचू",
                "Mesobuthus tamulus",
                "Scorpion",
                "High Risk",
                "Cardiovascular and Neurotoxic Venom",
                "हृदय व मज्जासंस्थेला हानी पोहोचवणारे विष",
                "Orange-red to dark red-brown body, thick segmented tail with sharp venomous sting, slender pedipalps. Length 50-80mm.",
                "तांबूस-लालसर किंवा विटकरी रंग, जाड वक्राकार शेपूट ज्याच्या टोकाला तीक्ष्ण डंख असतो. लांबी ५० ते ८० मिमी.",
                "Dry farm bunds, under bricks, stones, cow dung cakes, sugarcane husk, inside shoes and boots.",
                "शेतातील बांध, खडे, गोवऱ्या, उसाचे पाचट, लाकडांचे ढीग आणि सकाळी शेतात घालण्याच्या बुटांमध्ये.",
                "Always shake boots and clothes before wearing. Use flashlight during night irrigation. Avoid reaching bare hands under rocks.",
                "सकाळी शेतात जाताना बूट व कपडे झटकून वापरा. रात्री पाणी भरताना प्रखर विजेरी वापरा. शेतात खडे किंवा लाकूड उचलताना हातमोजे वापरा.",
                "Keep victim calm. Do not make cuts or tie tight tourniquets. Rush immediately to Rural or Civil Hospital for oral Prazosin therapy.",
                "रुग्णाला घाबरू देऊ नका. डंखावर काप मारू नका किंवा घट्ट दोरी बांधू नका. तातडीने ग्रामीण किंवा जिल्हा रुग्णालयात उपचारासाठी न्या.",
                R.drawable.red_scorpion
        ));

        // 2. Indian Black Scorpion
        list.add(new HarmfulCreature(
                "Indian Black Scorpion",
                "काळा विंचू",
                "Heterometrus swammerdami / H. phipsoni",
                "Scorpion",
                "Moderate Risk",
                "Local Cytotoxic and Pain Inducing Venom",
                "स्थानिक तीव्र जळजळ व असह्य वेदना निर्माण करणारे विष",
                "Large size up to 15cm, shiny pitch black or dark greenish-black, very large robust pincers.",
                "मोठा आकार (१० ते १५ सेमी), चकचकीत गडद काळा रंग, अतिशय मोठ्या व मजबूत नांग्या.",
                "Humid agricultural soil, beneath decayed tree trunks, termite mounds, stone walls.",
                "ओली जमीन, कुजलेली लाकडं, वारूळ आणि जुन्या दगडी भिंतींच्या कपारीत.",
                "Clear organic farm debris. Wear heavy farming gloves while clearing timber.",
                "शेतातील पालापाचोळा व जुनी लाकडे काढताना जाड हातमोजे वापरा.",
                "Wash sting site with soap and water. Apply cold ice pack to relieve burning pain. Seek medical analgesics if pain persists.",
                "डंखाची जागा साबण व थंड पाण्याने स्वच्छ धुवा. बर्फाचा शेक द्या जेणेकरून जळजळ कमी होईल. डॉक्टरांचा सल्ला घ्या.",
                R.drawable.red_scorpion
        ));

        // 3. Giant Centipede
        list.add(new HarmfulCreature(
                "Indian Giant Centipede",
                "मोठी गोम",
                "Scolopendra morsitans",
                "Centipede",
                "Moderate Risk",
                "Proteolytic Venom",
                "प्रथिने नष्ट करणारे विष",
                "Long segmented reddish-brown body with 21 pairs of vibrant yellow legs and strong black venomous jaws.",
                "लांबट, तांबूस-तपकिरी रंगाचे शरीर, २१ जोड्या पिवळे पाय आणि तोंडाजवळ काळ्या रंगाचे तीक्ष्ण विषारी दात.",
                "Under damp stones, compost pits, moist sack piles, and dark store corners.",
                "ओले दगड, शेणखताचे खड्डे, ओलसर बारदाणा आणि जुनी पोती साठवलेल्या जागेत.",
                "Keep sack storage elevated and dry. Spray lime powder around storage areas to deter them.",
                "धान्याची व खतांची पोती जमिनीपासून वर ठेवा. गोदामाभोवती चुना पावडर टाका.",
                "Wash bite with antiseptic soap. Apply ice compression. Consult a doctor for tetanus shot and pain relief.",
                "चावलेली जागा अँटीसेप्टिक साबणाने स्वच्छ धुवा. बर्फ लावा. धनुर्वाताचे इंजेक्शन आणि उपचारासाठी डॉक्टरांकडे जा.",
                R.drawable.giant_centipede
        ));

        // 4. Blister Beetle / Paederus
        list.add(new HarmfulCreature(
                "Blister Beetle",
                "तेल्या कीटक",
                "Epicauta hirticornis / Paederus",
                "Beetle",
                "High Risk",
                "Cantharidin / Paederin Acid",
                "कॅन्थॅरिडिन आम्ल",
                "Slender body with distinctive alternating black and bright orange-red stripes. Shiny head and long antennae.",
                "लांबट शरीर, काळे आणि चमकदार नारिंगी पट्टे, चमकदार डोके आणि लांब स्पर्शिका.",
                "Attracted to bright farm lights at night. Found on soybean, cotton, pulses, and flowering vegetable crops.",
                "रात्री शेतातील दिव्यांकडे आकर्षित होतो. सोयाबीन, कापूस, तूर व भाजीपाल्याच्या फुलोऱ्यात मोठ्या संख्येने आढळतो.",
                "Never crush this beetle on skin. If it lands on you, gently blow it away. Wear full-sleeve shirts during night farm work.",
                "हा कीटक अंगावर बसल्यास चुकूनही चुरगळू नका. हलके फुंकर मारून उडवून द्या. रात्री शेतात पूर्ण बाह्यांचे कपडे घाला.",
                "If crushed on skin, immediately wash with plenty of cold water and soap. Apply calamine lotion. Do not pop blisters.",
                "कीटक चुरगळल्यास त्वचा ताबडतोब साबण आणि पाण्याने स्वच्छ धुवा. कॅलामाइन लोशन लावा. आलेले फोड फोडू नका.",
                R.drawable.blister_beetle
        ));

        // 5. Giant Hornet / Wasp
        list.add(new HarmfulCreature(
                "Giant Hornet / Yellow Wasp",
                "गांधीलमाशी",
                "Vespa tropica / Polistes",
                "Wasp/Bee",
                "High Risk",
                "Phospholipase and Histamine",
                "तीव्र अॅलर्जिक शॉक व श्वास कोंडणारे विष",
                "Large flying wasp with yellow and black abdomen bands, powerful sting that can sting repeatedly.",
                "मोठी पिवळसर-काळी माशी, जी वारंवार डंख मारू शकते.",
                "Tree branches in orchards, well sides, old farm sheds and roofs.",
                "फळबागा, विहिरींचे कडे आणि शेतातील जुन्या पत्र्यांच्या शेडखाली.",
                "Inspect tree canopies before pruning or harvesting fruits. Avoid disturbing active hanging hives.",
                "झाडांची छाटणी किंवा फळतोडणी करताना फांद्यांवर पोळे आहे का ते तपासा.",
                "Scrape out visible stingers. Apply cold ice pack. If patient experiences breathlessness or dizziness, rush to hospital.",
                "डंख दिसल्यास काढा. बर्फ लावा. जर रुग्णाला चक्कर किंवा धाप लागणे असा त्रास झाला तर तात्काळ रुग्णालयात दाखल करा.",
                R.drawable.blister_beetle
        ));

        // 6. Hairy Caterpillar
        list.add(new HarmfulCreature(
                "Hairy Caterpillar",
                "केसळ अळी",
                "Spilosoma obliqua",
                "Caterpillar",
                "Moderate Risk",
                "Histamine and Urticating Spines",
                "हिस्टामाइनयुक्त विषारी केस",
                "Dense reddish-brown or black bristles covering the whole caterpillar body.",
                "संपूर्ण अंगावर दाट काळे व तांबूस केस असलेली अळी. पानांवर समूहाने आढळते.",
                "Soybean leaves, sunflower, castor, pulses, and weed patches around farm fences.",
                "सोयाबीन, सूर्यफूल, एरंडी आणि शेताच्या कुंपणावरील तणावर.",
                "Wear protective gloves while weeding and harvesting legumes.",
                "खुरपणी करताना व शेंगा तोडताना हातमोजे वापरा.",
                "Do not rub skin. Use adhesive tape to gently remove embedded hairs, then wash with cold water and apply soothing lotion.",
                "त्वचा चोळू नका. चिकटपट्टी लावून त्वचेतील बारीक केस अलगद काढा, नंतर थंड पाण्याने धुवून खोबरेल तेल लावा.",
                R.drawable.giant_centipede
        ));

        // 7. Wild Honey Bee Swarm
        list.add(new HarmfulCreature(
                "Giant Rock Honey Bee",
                "आग्या मधमाशी",
                "Apis dorsata",
                "Bee/Swarm",
                "High Risk",
                "Melittin and Histamine Multi-Sting Toxicity",
                "सामूहिक डंखांमुळे शरीरात विष भरणे व श्वास कोंडणे",
                "Large aggressive wild honeybees that build massive exposed hives on tall trees and water tanks.",
                "मोठ्या आकाराची रागीट मधमाशी जी विहिरीच्या काठावर व शेतातील झाडांवर मोठी पोळी बनवते.",
                "Forest borders, tamarind trees, farm wells, and overhead water towers.",
                "चिंच, वड, लिंबाची झाडे, विहिरींच्या आतल्या भिंती आणि पाणी साठवण टाक्यांखाली.",
                "Do not throw stones or create smoke under hives. If attacked, cover face with cloth and seek enclosed shelter.",
                "पोळ्यावर दगड मारू नका. हल्ला झाल्यास तोंडावर जाड कापड घेऊन सुरक्षित खोलीत किंवा वाहनात आश्रय घ्या.",
                "Quickly scrape stingers with a card without squeezing. Apply ice water. If stung multiple times, rush to hospital.",
                "डंख कार्डने खरवडून काढा. बर्फाचे पाणी लावा. जास्त डंख असल्यास रुग्णाला तात्काळ शासकीय रुग्णालयात दाखल करा.",
                R.drawable.blister_beetle
        ));

        // 8. Large Indian Spider
        list.add(new HarmfulCreature(
                "Indian Huntsman Spider",
                "मोठा कोळी",
                "Poecilotheria / Heteropoda venatoria",
                "Spider",
                "Moderate Risk",
                "Necrotic and Neurotoxic Secretions",
                "स्थानिक त्वचेची जळजळ व स्नायूंमध्ये कळा",
                "Large hairy brown or grey patterned spider with long striped legs, fast-moving night hunter.",
                "मोठा, राखाडी-तपकिरी रंगाचा, लांब पट्टेदार पाय असलेला वेगाने पळणारा रात्रीचा कोळी.",
                "Under tree barks, old farm pump houses, dry storage lofts, beneath wooden racks.",
                "झाडांची साल, शेतातील मोटार पंप हाऊस, कांदा चाळ आणि जुन्या लाकडी फळ्यांच्या मागे.",
                "Wear protective gloves when sorting onions, garlic, or stored grain sacks.",
                "कांदा चाळीत व धान्य पोती उचलताना हातमोजे वापरा.",
                "Wash bite site thoroughly with antiseptic and warm water. Apply cold compress and consult a local doctor.",
                "चावलेली जागा कोमट पाणी व अँटीसेप्टिकने धुवा. बर्फ लावा. डॉक्टरांच्या सल्ल्याने उपचार घ्या.",
                R.drawable.giant_centipede
        ));

        return list;
    }
}
