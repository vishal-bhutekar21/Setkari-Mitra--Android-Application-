package com.example.Shetkari_Mitra;

import java.util.ArrayList;
import java.util.List;

public class CreatureDataProvider {

    public static List<HarmfulCreature> getCreatures() {
        List<HarmfulCreature> list = new ArrayList<>();

        // 1. Indian Red Scorpion
        list.add(new HarmfulCreature(
                "Indian Red Scorpion",
                "तांबडा विंचू (खतरनाक)",
                "Mesobuthus tamulus",
                "Scorpion",
                "High (अतिधोकादायक)",
                "Cardiovascular & Neurotoxic Venom (हृदय व मज्जासंस्थेवर आघात)",
                "हृदय व मज्जासंस्थेला हानी पोहोचवणारे विष (फुफ्फुसात पाणी भरणे)",
                "Orange-red to dark red-brown body, thick segmented tail with sharp venomous sting, slender pedipalps (claws). Length 50-80mm.",
                "तांबूस-लालसर किंवा विटकरी रंग, जाड वक्राकार शेपूट ज्याच्या टोकाला तीक्ष्ण डंख असतो. नांग्या बारीक असतात. लांबी ५० ते ८० मिमी.",
                "Dry farm bunds, under bricks, stones, cow dung cakes (गोवऱ्या), sugarcane husk, inside farmer shoes/gum boots.",
                "शेतातील बांध, खडे, गोवऱ्या, उसाचे पाचट, लाकडांचे ढीग आणि सकाळी शेतात घालण्याच्या बुटांमध्ये.",
                "Always shake gum boots and clothes before wearing. Use flashlight during night irrigation. Avoid reaching bare hands under rocks.",
                "सकाळी शेतात जाताना बूट व कपडे झटकून वापरा. रात्री पाणी भरताना प्रखर विजेरी वापरा. शेतात खडे किंवा लाकूड उचलताना हातमोजे वापरा.",
                "Keep victim calm. Do not make cuts or tie tight tourniquets. Rush immediately to Rural/Civil Hospital for oral Prazosin (प्राझोसिन) therapy which is life-saving.",
                "रुग्णाला घाबरू देऊ नका. डंखावर काप मारू नका किंवा घट्ट दोरी बांधू नका. तातडीने ग्रामीण/जिल्हा रुग्णालयात न्या जिथे 'प्राझोसिन' (Prazosin) औषध देऊन उपचार केले जातात.",
                R.drawable.red_scorpion
        ));

        // 2. Indian Black Scorpion
        list.add(new HarmfulCreature(
                "Indian Black Scorpion",
                "काळा विंचू (मोठा विंचू)",
                "Heterometrus swammerdami / H. phipsoni",
                "Scorpion",
                "Moderate (मध्यम धोका)",
                "Local Cytotoxic & Pain Inducing (स्थानिक तीव्र वेदना)",
                "स्थानिक तीव्र जळजळ व असह्य वेदना निर्माण करणारे विष",
                "Large size (up to 15cm), shiny pitch black or dark greenish-black, very large robust pincers/claws.",
                "मोठा आकार (१० ते १५ सेमी), चकचकीत गडद काळा रंग, अतिशय मोठ्या व मजबूत नांग्या.",
                "Humid agricultural soil, beneath decayed tree trunks, termite mounds, stone walls.",
                "ओली जमीन, कुजलेली लाकडं, वारूळ आणि जुन्या दगडी भिंतींच्या कपारीत.",
                "Clear organic farm debris. Wear heavy leather farming gloves while clearing timber.",
                "शेतातील पालापाचोळा व जुनी लाकडे काढताना जाड हातमोजे वापरा.",
                "Wash sting site with soap and water. Apply cold ice pack to relieve burning pain. Seek medical analgesics if pain is unbearable.",
                "डंखाची जागा साबण व थंड पाण्याने स्वच्छ धुवा. बर्फाचा शेक द्या जेणेकरून जळजळ कमी होईल. वेदनाशामक गोळ्यांसाठी डॉक्टरांचा सल्ला घ्या.",
                R.drawable.red_scorpion
        ));

        // 3. Giant Centipede
        list.add(new HarmfulCreature(
                "Indian Giant Centipede",
                "मोठी गोम (घोरपड गोम)",
                "Scolopendra morsitans",
                "Centipede",
                "Moderate (मध्यम धोका)",
                "Proteolytic Venom (त्वचा जळजळ व स्थानिक सूज)",
                "प्रथिने नष्ट करणारे विष (अतिशय तीव्र आग व सूज)",
                "Long segmented reddish-brown body with 21 pairs of vibrant yellow legs and strong black venomous forcipules (jaws).",
                "लांबट, तांबूस-तपकिरी रंगाचे शरीर, २१ जोड्या पिवळे पाय आणि तोंडाजवळ काळ्या रंगाचे तीक्ष्ण नांगीसारखे विषारी दात.",
                "Under damp stones, compost pits (खत खड्डा), moist sack piles, kitchen corners.",
                "ओले दगड, शेणखताचे खड्डे, ओलसर बारदाणा आणि जुनी पोती साठवलेल्या जागेत.",
                "Keep sack storage elevated and dry. Spray lime powder (कळीचा चुना) around storage areas to deter them.",
                "धान्याची व खतांची पोती जमिनीपासून वर ठेवा. गोदामाभोवती चुना पावडर टाका.",
                "Wash bite with antiseptic soap. Apply ice compression. Watch for secondary bacterial infection and consult a doctor for tetanus shot.",
                "चावलेली जागा अँटीसेप्टिक साबणाने स्वच्छ धुवा. बर्फ लावा. धनुर्वाताचे (Tetanus) इंजेक्शन आणि अँटीबायोटिकसाठी डॉक्टरांकडे जा.",
                R.drawable.giant_centipede
        ));

        // 4. Blister Beetle / Paederus
        list.add(new HarmfulCreature(
                "Blister Beetle / Paederus",
                "तेल्या कीटक / फोड आणणारा भुंगा",
                "Epicauta hirticornis / Paederus dermatitis",
                "Beetle",
                "High (तीव्र रासायनिक फोड)",
                "Cantharidin / Paederin Acid (त्वचा जाळणारे आम्ल)",
                "कॅन्थॅरिडिन आम्ल (त्वचेवर पाणीदार मोठे फोड निर्माण करणारे विष)",
                "Slender body with distinctive alternating black and bright orange-red stripes. Shiny head and long antennae.",
                "लांबट शरीर, काळे आणि चमकदार नारिंगी पट्टे, चमकदार डोके आणि लांब स्पर्शिका (एंटीना).",
                "Attracted to bright farm lights at night. Found on soybean, cotton, pulses, and flowering vegetable crops.",
                "रात्री शेतातील दिव्यांकडे आकर्षित होतो. सोयाबीन, कापूस, तूर व भाजीपाल्याच्या फुलोऱ्यात मोठ्या संख्येने आढळतो.",
                "CRITICAL: NEVER CRUSH this beetle on your skin! If it lands on you, gently blow it away. Wear full-sleeve shirts during night farm work.",
                "महत्त्वाची दक्षता: हा कीटक अंगावर बसल्यास चुकूनही चुरगळू नका! हलके फुंकर मारून उडवून द्या. रात्री शेतात पूर्ण बाह्यांचे कपडे घाला.",
                "If crushed, immediately wash the skin with plenty of cold water and soap. Apply calamine lotion or steroid ointment. Do NOT pop the blisters.",
                "कीटक चुरगळल्यास त्वचा ताबडतोब साबण आणि पाण्याने स्वच्छ धुवा. कॅलामाइन लोशन लावा. आलेले फोड फोडू नका.",
                R.drawable.blister_beetle
        ));

        // 5. Giant Hornet / Wasp
        list.add(new HarmfulCreature(
                "Giant Hornet / Yellow Wasp",
                "गांधीलमाशी / आग्या माशी",
                "Vespa tropica / Polistes",
                "Wasp/Bee",
                "High (अतिसंवेदनशीलता व शॉक धोका)",
                "Phospholipase & Histamine Shock (अॅलर्जी व श्वास घेण्यास अडथळा)",
                "तीव्र अॅलर्जिक शॉक व श्वास कोंडणारे विष",
                "Large flying wasp with yellow and black abdomen bands, powerful sting that can sting repeatedly.",
                "मोठी पिवळसर-काळी माशी, जी वारंवार डंख मारू शकते.",
                "Tree branches in orchards (बोर, आंबा, डाळिंब बागा), well sides, old farm buildings.",
                "फळबागा (आंबा, लिंबू, बोर), विहिरींचे कडे आणि शेतातील जुन्या पत्र्यांच्या शेडखाली.",
                "Inspect tree canopies before pruning or harvesting fruits. Avoid disturbing active hanging hives.",
                "झाडांची छाटणी किंवा फळतोडणी करताना फांद्यांवर पोळे आहे का ते तपासा.",
                "Scrape out visible stingers. Apply cold ice pack. If patient experiences breathlessness, lip swelling, or dizziness, RUSH to hospital immediately for anti-allergic injection.",
                "डंख दिसल्यास काढा. बर्फ लावा. जर रुग्णाला चक्कर, धाप लागणे किंवा ओठ सुजणे असा त्रास झाला तर तात्काळ रुग्णालयात दाखल करा.",
                R.drawable.blister_beetle
        ));

        // 6. Hairy Caterpillar
        list.add(new HarmfulCreature(
                "Hairy Caterpillar (Woolly Bear)",
                "कांबळी कीड / केसळ अळी",
                "Spilosoma obliqua",
                "Caterpillar",
                "Moderate (खाज व अंगावर पुरळ)",
                "Histamine & Urticating Spines (त्वचेवर तीव्र कंड व लाल पुरळ)",
                "हिस्टामाइनयुक्त विषारी केस (अंगावर लाल चट्टे व तीव्र खाज)",
                "Dense reddish-brown or black bristles/hairs covering the whole caterpillar body.",
                "संपूर्ण अंगावर दाट काळे व तांबूस केस असलेली अळी. पानांवर समूहाने आढळते.",
                "Soybean leaves, sunflower, castor (एरंडी), pulses, and weed patches around farm fences.",
                "सोयाबीन, सूर्यफूल, एरंडी आणि शेताच्या कुंपणावरील तणावर.",
                "Wear protective gloves while weeding and harvesting legumes.",
                "खुरपणी करताना व शेंगा तोडताना हातमोजे वापरा.",
                "Do NOT rub the skin! Use cello tape/adhesive tape to gently pull out embedded micro-hairs, then wash with cold water and apply coconut oil or calamine.",
                "त्वचा चोळू नका! चिकटपट्टी (Cello tape) लावून त्वचेतील बारीक केस अलगद ओढून काढा, नंतर थंड पाण्याने धुवून खोबरेल तेल किंवा कॅलामाइन लोशन लावा.",
                R.drawable.giant_centipede
        ));

        // 7. Wild Honey Bee Swarm
        list.add(new HarmfulCreature(
                "Giant Rock Honey Bee (Swarm Attack)",
                "आग्या मधमाशी (सामूहिक हल्ला)",
                "Apis dorsata",
                "Bee/Swarm",
                "High (सामूहिक विषबाधा व शॉक)",
                "Melittin & Histamine Multi-Sting Shock (अतिविषबाधा व अॅलर्जिक शॉक)",
                "सामूहिक डंखांमुळे शरीरात विष भरणे व श्वास कोंडणे",
                "Large aggressive wild honeybees that build massive exposed hives on tall trees, rocks, and water tanks.",
                "मोठ्या आकाराची रागीट मधमाशी जी विहिरीच्या काठावर, उंच वृक्षांवर व शेतातील झाडांवर मोठी पोळी बनवते.",
                "Forest borders, tamarind/banyan trees, sugarcane fields, farm wells, and overhead water towers.",
                "चिंच, वड, लिंबाची झाडे, विहिरींच्या आतल्या भिंती आणि पाणी साठवण टाक्यांखाली.",
                "Do NOT throw stones or create smoke under hives during sunny hours. If attacked, cover face with thick blanket/cloth and run in zigzag pattern to enclosed shelter.",
                "पोळ्यावर दगड मारू नका किंवा खाली अचानक धूर करू नका. हल्ला झाल्यास तोंडावर जाड घोंगडी/कापड घेऊन सुरक्षित खोलीत किंवा वाहनात आश्रय घ्या.",
                "Quickly scrape stingers with a hard card (do NOT squeeze with fingers). Apply cold ice water. If stung by >20 bees or victim has dizziness, rush to ICU/Hospital immediately.",
                "डंख कार्डने खरवडून काढा (बोटाने दाबू नका). बर्फाचे पाणी लावा. २० पेक्षा जास्त डंख असल्यास रुग्णाला तात्काळ आयसीयू/शासकीय रुग्णालयात दाखल करा.",
                R.drawable.blister_beetle
        ));

        // 8. Large Indian Spider
        list.add(new HarmfulCreature(
                "Indian Ornamental / Huntsman Spider",
                "विषाक्त कोळी (मोठा शेतकरी कोळी)",
                "Poecilotheria / Heteropoda venatoria",
                "Spider",
                "Moderate (स्थानिक सूज व जळजळ)",
                "Necrotic & Neurotoxic (स्थानिक ऊती दाह व तीव्र कळा)",
                "स्थानिक त्वचेची जळजळ, लालसर सूज व स्नायूंमध्ये कळा",
                "Large hairy brown or grey patterned spider with long striped legs, fast-moving night hunter.",
                "मोठा, राखाडी-तपकिरी रंगाचा, लांब पट्टेदार पाय असलेला वेगाने पळणारा रात्रीचा कोळी.",
                "Under tree barks, old farm pump houses, dry onion/garlic storage lofts, beneath wooden racks.",
                "झाडांची साल, शेतातील मोटार पंप हाऊस, कांदा चाळ आणि जुन्या लाकडी फळ्यांच्या मागे.",
                "Wear protective gloves when sorting onions, garlic, or stored grain sacks.",
                "कांदा चाळीत व धान्य पोती उचलताना हातमोजे वापरा.",
                "Wash bite site thoroughly with antiseptic and warm water. Apply cold compress. Take pain medication and consult a local doctor.",
                "चावलेली जागा कोमट पाणी व अँटीसेप्टिकने धुवा. बर्फ लावा. डॉक्टरांच्या सल्ल्याने वेदनाशामक औषध घ्या.",
                R.drawable.giant_centipede
        ));

        return list;
    }
}
