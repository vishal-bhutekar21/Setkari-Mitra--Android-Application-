package com.example.Shetkari_Mitra;

import java.util.ArrayList;
import java.util.List;

public class SnakeDataProvider {

    public static List<DataClass> getSnakeDataList() {
        List<DataClass> list = new ArrayList<>();

        // 1. Indian Cobra / Spectacled Cobra (नाग) - Big Four
        list.add(new DataClass(
                "Spectacled Cobra",
                "नाग",
                "Naja naja",
                "Venomous",
                "विषारी",
                "Neurotoxic Venom",
                "मज्जासंस्थेवर आघात करणारे विष",
                "The Indian Cobra is one of India's Big Four highly venomous snakes. When threatened, it raises the front part of its body and spreads its hood with a spectacle-shaped mark on the back.",
                "नाग हा भारतातील प्रमुख चार अतिविषारी सापांपैकी एक आहे. धोका जाणवल्यास तो शरीराचा पुढील भाग वर उचलून फणा काढतो. फण्याच्या पाठीमागे चष्म्यासारखी खूण असते.",
                "Distinct expandable hood with spectacle mark, smooth scales, color ranges from yellow-brown to blackish with round eye pupils.",
                "विशाल फणा, फण्यामागे चष्म्याची खूण, गुळगुळीत खवले, पिवळसर-तपकिरी ते काळा रंग आणि गोलाकार डोळ्यांची बाहुली.",
                "Agricultural fields, grain storage sheds, rat burrows, rock piles, and near farm houses across Maharashtra.",
                "शेतातील बांध, धान्याची कोठारे, उंदरांची बिळे, दगडी ढिगारे आणि वस्त्यांच्या आसपास सर्वत्र आढळतो.",
                "Rats, mice, toads, frogs, birds, and smaller snakes.",
                "उंदीर, घुशी, बेडूक, पक्षी आणि लहान साप.",
                "Never tease or try to catch a hooded cobra. In fields, make vibrations with a stick before stepping into thick crops. Wear gumboots.",
                "फणा काढलेल्या नागाला कधीही त्रास देऊ नका किंवा पकडण्याचा प्रयत्न करू नका. शेतात जाताना काठी जमिनीवर आपटून कंपन निर्माण करा.",
                "Immediate immobilization of the bitten limb. Rush to the nearest Anti-Snake Venom hospital within the golden hour. Do not apply tourniquet.",
                "दंश झालेला अवयव अजिबात हलवू नका. ताबडतोब जवळच्या शासकीय किंवा खाजगी रुग्णालयात पोहोचवा. दोरी बांधू नका.",
                R.drawable.p2,
                1
        ));

        // 2. Common Krait (मण्यार) - Big Four
        list.add(new DataClass(
                "Common Krait",
                "मण्यार",
                "Bungarus caeruleus",
                "Venomous",
                "विषारी",
                "Potent Neurotoxic Venom",
                "तीव्र मज्जासंस्थेवर आघात करणारे विष",
                "The Common Krait possesses the most potent venom among Indian land snakes. It is strictly nocturnal and responsible for severe nighttime snakebites while victims sleep on the floor.",
                "मण्यार हा जमिनीवरील सापांमध्ये सर्वात तीव्र विष असलेला साप आहे. तो पूर्णपणे निशाचर असून जमिनीवर झोपलेल्या व्यक्तींना नकळत चावल्याने गंभीर घटना घडतात.",
                "Glossy blue-black or shiny dark body with narrow white cross-bands starting after the neck. Hexagonal scales along the spine.",
                "चमकदार निळसर-काळा रंग, मानेनंतर शरीरावर पांढरे दुहेरी पट्टे, पाठीच्या मध्यभागी षटकोनी आकाराचे मोठे खवले.",
                "Villages, mud houses, crop fields, piles of firewood, brick kilns, and rodent burrows throughout Maharashtra.",
                "ग्रामीण वस्त्या, मातीची घरे, गवताचे ढीग, लाकडांचे ढिगारे आणि विटांच्या भट्ट्यांमध्ये वास्तव्य.",
                "Feeds on other snakes, small rodents, lizards, and frogs.",
                "इतर लहान साप, लहान उंदीर, सरडे आणि बेडूक.",
                "Always sleep on a raised cot and use mosquito nets in farm houses. Avoid sleeping on the bare floor during monsoon.",
                "शेतवस्तीवर किंवा घरात नेहमी जमिनीपासून वर खाटेवर झोपावे आणि मच्छरदाणी वापरावी. जमिनीवर झोपणे टाळावे.",
                "Victim often feels no local pain. Watch for eyelid drooping, abdominal pain, and breathing difficulty. Administer ASV immediately.",
                "दंशाच्या ठिकाणी सहसा वेदना जाणवत नाही. पोटदुखी, पापण्या जड होणे किंवा श्वास घेण्यास त्रास झाल्यास तातडीने उपचार सुरू करावेत.",
                R.drawable.common_krait,
                1
        ));

        // 3. Russell's Viper (घोणस) - Big Four
        list.add(new DataClass(
                "Russell's Viper",
                "घोणस",
                "Daboia russelii",
                "Venomous",
                "विषारी",
                "Hemotoxic and Cytotoxic Venom",
                "रक्ताभिसरण व ऊतींवर आघात करणारे विष",
                "Russell's Viper is responsible for high venomous snakebites in agricultural areas. It produces an extremely loud warning hiss when threatened.",
                "महाराष्ट्रात सर्वाधिक सर्पदंशाच्या घटनांसाठी घोणस जबाबदार आहे. धोका जाणवल्यास तो कुकरच्या शिट्टीसारखा प्रचंड मोठा फुत्कार सोडतो.",
                "Robust body with three longitudinal rows of dark brown chain-like spots on a tan background with triangular flattened head.",
                "जाडजुड शरीर, त्रिकोणी चपटे डोके, पाठीवर साखळीसारखे तीन ओळीत अंडाकृती तपकिरी डाग आणि उभ्या बाहुल्या.",
                "Sugarcane fields, cotton farms, scrublands, grassland borders, and rock piles near moisture.",
                "ऊस, कपाशी, सोयाबीनची शेते, गवताळ भाग आणि पाणथळ जागेजवळील दगडधोंडे.",
                "Primarily rodents, rats, mice, small birds, and lizards.",
                "प्रामुख्याने शेतातील उंदीर, घुशी आणि लहान पक्षी.",
                "Watch your step while harvesting sugarcane or weeding crops. Use a stick and torch at all times.",
                "ऊस तोडणी व शेतात खुरपणी करताना काळजीपूर्वक पावले टाका. रात्री नेहमी विजेरी वापरा.",
                "Causes severe local swelling, pain, and bleeding. Rush to hospital for anti-venom. Keep patient calm.",
                "दंशानंतर प्रचंड सूज, वेदना व रक्तस्त्राव होतो. तात्काळ उपचारासाठी जिल्हा रुग्णालयात हलवा.",
                R.drawable.viper,
                1
        ));

        // 4. Saw-Scaled Viper (फुरसे) - Big Four
        list.add(new DataClass(
                "Saw-Scaled Viper",
                "फुरसे",
                "Echis carinatus",
                "Venomous",
                "विषारी",
                "Potent Hemotoxic Venom",
                "रक्तस्त्राव घडवणारे विष",
                "A small but venomous viper. When threatened, it rubs its serrated lateral scales together, producing a rasping sound.",
                "हा आकाराने लहान पण अत्यंत चपळ आणि विषारी साप आहे. धोका वाटल्यास तो अंगावरील खडबडीत खवले एकमेकांवर घासून करवतीसारखा आवाज करतो.",
                "Small size (30-60 cm), bird-foot mark on head, keeled scales, undulating wavy side line with light spots.",
                "लहान आकार (३० ते ६० सेमी), डोक्यावर पांढऱ्या पक्ष्यांच्या पायासारखी खूण, खडबडीत खवले आणि अंगावर पांढरे नागमोडी डाग.",
                "Dry rocky terrain, arid agricultural fields, gravel beds, and thorn scrub forests.",
                "खडकाळ माळरान, कोरडी शेते, दगडांचे ढिगारे आणि झुडपी जंगलात आढळतो.",
                "Scorpions, centipedes, lizards, geckos, frogs, and small mice.",
                "विंचू, गोम, सरडे, पाल, लहान उंदीर आणि किडे.",
                "Never put hands under loose rocks or dry bushes without looking. Wear closed footwear.",
                "माळरानावरील किंवा शेतातील दगड विनाकारण हाताने उचलू नका. काम करताना पायात बंद बूट वापरा.",
                "Bite prevents blood clotting. Requires specific anti-snake venom treatment at hospital.",
                "दंशामुळे रक्ताची गुठळी होत नाही. त्वरित रुग्णालयात उपचार घेणे गरजेचे असते.",
                R.drawable.saw_scaled_viper,
                1
        ));

        // 5. Bamboo Pit Viper (चापडा)
        list.add(new DataClass(
                "Bamboo Pit Viper",
                "हिरवा चापडा",
                "Craspedocephalus gramineus",
                "Venomous",
                "विषारी",
                "Hemotoxic Venom",
                "स्थानिक सूज व वेदना निर्माण करणारे विष",
                "An arboreal green venomous pit viper with specialized heat-sensing pits between the eyes and nostrils.",
                "झाडांवर राहणारा चमकदार हिरवा विषारी साप. डोळे आणि नाकपुडीच्या मध्ये उष्णता ओळखणारे विशेष छिद्र असते.",
                "Bright green body, yellowish belly, triangular head, prehensile tail with reddish-brown tip, vertical pupils.",
                "चमकदार पोपटी-हिरवा रंग, पिवळसर पोट, स्पष्ट त्रिकोणी डोके आणि मांजरासारख्या उभ्या बाहुल्या.",
                "Bamboo thickets, fruit orchards, dense shrubs, stream vegetation, and forest borders.",
                "बांबूची वने, फळबागा, दाट झुडपे आणि पाण्याच्या प्रवाहाजवळील झाडेझुडपे.",
                "Frogs, tree toads, small birds, and geckos.",
                "बेडूक, झाडांवरील पक्षी, पाली आणि सरडे.",
                "Look closely before picking fruits or trimming branches in orchards.",
                "फळांची तोडणी करताना किंवा झाडांच्या फांद्या छाटताना काळजीपूर्वक पहा.",
                "Bites cause severe swelling and pain. Hospitalization and medical care required.",
                "चावल्यास तीव्र सूज व वेदना होतात. रुग्णाला तत्काळ रुग्णालयात न्यावे.",
                R.drawable.bamboo_pit_viper,
                1
        ));

        // 6. Indian Rat Snake (धामण)
        list.add(new DataClass(
                "Indian Rat Snake",
                "धामण",
                "Ptyas mucosa",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "The Indian Rat Snake is large, powerful, and fast. It is considered the farmer's greatest natural ally because it consumes crop-destroying rodents.",
                "धामण हा अत्यंत वेगवान, लांब आणि शक्तिशाली बिनविषारी साप आहे. तो शेतातील पिके नष्ट करणाऱ्या उंदरांचा फडशा पाडतो, म्हणून तो शेतकऱ्यांचा मित्र आहे.",
                "Long, slender body up to 8-10 feet, yellow-olive to brown color, large round eyes, black vertical bars on lip scales.",
                "लांब चपळ शरीर (६ ते ९ फूट), पिवळसर-तपकिरी रंग, मोठे डोळे आणि ओठांच्या खवल्यांवर काळ्या उभ्या रेषांची खूण.",
                "Agricultural fields, crop sheds, granaries, wells, and grasslands throughout Maharashtra.",
                "शेतातील पिके, गोठे, धान्याची कोठारे, विहिरी आणि बांधांवर सर्वत्र आढळतो.",
                "Chiefly agricultural rodents, rats, mice, frogs, toads, and lizards.",
                "प्रामुख्याने शेतातील उंदीर, घुशी, बेडूक आणि सरडे.",
                "Never kill a rat snake. It cannot harm humans and protects food grains from rodent damage.",
                "धामण सापाला चुकूनही मारू नका. तो माणसाला कोणताही धोका पोहोचवत नाही आणि पिकांचे रक्षण करतो.",
                "Non-venomous bite. Wash with clean soap and water. Antiseptic application is sufficient.",
                "दंश पूर्णपणे बिनविषारी असतो. जखम साबणाने स्वच्छ धुवून मलम लावावा.",
                R.drawable.ratsnake,
                0
        ));

        // 7. Indian Rock Python (अजगर)
        list.add(new DataClass(
                "Indian Rock Python",
                "भारतीय अजगर",
                "Python molurus",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A heavy-bodied non-venomous constrictor snake protected under wildlife laws. It subdues prey through constriction, not venom.",
                "भारतीय अजगर हा अत्यंत वजनदार, शांत आणि बिनविषारी साप आहे. तो वन्यजीव संरक्षण कायद्यान्वये संरक्षित आहे.",
                "Heavy patterned body with yellowish-tan blotches outlined in dark brown, lance-shaped mark on head.",
                "मोठे जाडजुड शरीर, अंगावर सुंदर पिवळसर-तपकिरी ठिपके आणि डोक्यावर बाणाच्या आकाराची खूण.",
                "Rocky hillsides, riverbanks, forest edges, scrublands, and near agricultural canals.",
                "नद्या-ओढ्यांचे काठ, खडकाळ गुहा, शेतांच्या सीमा आणि पाण्याचे कालवे.",
                "Mammals, rodents, birds, and hares.",
                "उंदीर, ससे, लहान सस्तन प्राणी आणि पक्षी.",
                "Pythons are non-aggressive unless provoked. Call a certified rescuer if found near human settlement.",
                "अजगर अत्यंत शांत असतो. शेतात किंवा विहिरीजवळ आढळल्यास सर्पमित्राला बोलावून जंगलात सोडून द्यावे.",
                "Non-venomous. Clean with antiseptic and take a tetanus shot if needed.",
                "बिनविषारी. चावल्यास दातांची जखम स्वच्छ धुवावी व धनुर्वाताचे इंजेक्शन घ्यावे.",
                R.drawable.indian_rock_python,
                0
        ));

        // 8. Checkered Keelback (दिवड)
        list.add(new DataClass(
                "Checkered Keelback",
                "दिवड",
                "Fowlea piscator",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "An active semi-aquatic non-venomous snake found in farm wells, ponds, and irrigation channels.",
                "शेतातील विहिरी, शेततळे आणि पाटबंधाऱ्यांमध्ये आढळणारा अत्यंत चपळ बिनविषारी साप.",
                "Olive-brown or yellowish body with a black checkered grid pattern and two dark streaks behind each eye.",
                "तपकिरी-पिवळसर रंग, शरीरावर काळ्या चौकटींची जाळीदार रचना आणि डोळ्यांमागे दोन काळे पट्टे.",
                "Wells, farm ponds, rivers, paddy fields, and irrigation streams.",
                "विहिरी, शेततळी, भातशेती, कालवे आणि ओहोळ.",
                "Fish, tadpoles, frogs, and aquatic insects.",
                "मासे, बेडूक आणि जलचर कीटक.",
                "Harmless to farmers working in water and irrigation ponds.",
                "पाण्यात काम करताना शेतकऱ्यांसाठी पूर्णपणे निरुपद्रवी आहे.",
                "Wash bite wound with soap and water. Non-venomous.",
                "जखम स्वच्छ पाण्याने व साबणाने धुवावी.",
                R.drawable.checkered_snake,
                0
        ));

        // 9. Red Sand Boa (मांडोळ)
        list.add(new DataClass(
                "Red Sand Boa",
                "मांडोळ",
                "Eryx johnii",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A gentle burrowing non-venomous snake. Its blunt tail resembles its head, which led to the popular two-headed myth.",
                "अत्यंत शांत स्वभावाचा, जमिनीत बिळे करून राहणारा बिनविषारी साप. शेपूट डोक्यासारखीच बोथट असते.",
                "Thick sausage-like body, reddish-brown to dark tan, blunt rounded tail, small eyes.",
                "जाड दंडगोलाकार शरीर, तांबूस-तपकिरी रंग, डोक्यासारखीच बोथट शेपूट आणि बारीक डोळे.",
                "Loose sandy soil, agricultural fields, arid scrublands, and rodent burrows.",
                "भुसभुशीत वालुकामय जमीन, उंदरांची बिळे आणि शेतातील माती.",
                "Rodents, mice, and small burrowing creatures.",
                "उंदीर, घुशी आणि लहान किडे.",
                "Sand boas do not bring wealth or have two heads. They are beneficial for pest control.",
                "मांडोळ सापामुळे धनलाभ होतो हा निव्वळ गैरसमज आहे. अंधश्रद्धेला बळी पडू नका.",
                "Harmless bite. Clean the wound with antiseptic soap.",
                "निरुपद्रवी. जखम साबणाने स्वच्छ धुवावी.",
                R.drawable.red_sand_boa,
                0
        ));

        // 10. Green Vine Snake (हरणटोळ)
        list.add(new DataClass(
                "Green Vine Snake",
                "हरणटोळ",
                "Ahaetulla nasuta",
                "Mildly Venomous",
                "निम-विषारी",
                "Mild Venom",
                "स्थानिक सौम्य चुरचूर",
                "A slender arboreal snake that blends seamlessly into green foliage with keyhole-shaped pupils.",
                "झाडांच्या फांद्यांमध्ये हुबेहूब लपणारा अत्यंत सडपातळ हिरवा साप. तोंड लांबट टोकदार असते.",
                "Bright fluorescent green, pointed snout, slender body, horizontal keyhole pupils.",
                "पोपटी हिरवा रंग, लांब टोकदार चोच, अतिशय सडपातळ अंग आणि डोळ्यात आडवी बाहुली.",
                "Fruit trees, bushes, garden hedges, grape and pomegranate orchards.",
                "द्राक्ष व डाळिंबाच्या बागा, झाडांच्या फांद्या, झुडपे आणि जंगले.",
                "Lizards, geckos, frogs, and small birds.",
                "पाली, सरडे, झाडांवरील बेडूक आणि लहान पक्षी.",
                "Harmless to humans. It does not target human eyes.",
                "हा डोळे फोडतो हा पूर्णपणे खोटा समज आहे. मानवाला कसलाही धोका नसतो.",
                "Mild bite causes slight itching. No anti-venom required.",
                "चावल्यास थोडी खाज येऊ शकते. औषधोपचाराची विशेष गरज नसते.",
                R.drawable.green_vine_snake,
                2
        ));

        // 11. Common Wolf Snake (कवड्या)
        list.add(new DataClass(
                "Common Wolf Snake",
                "कवड्या",
                "Lycodon aulicus",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A small non-venomous snake frequently found inside homes hunting geckos. It visually resembles the Common Krait.",
                "घरांच्या भिंती, कौले आणि गोठ्यांमध्ये पालींच्या शिकारीसाठी आढळणारा लहान बिनविषारी साप.",
                "Brownish body with white or yellowish crossbars, white collar band on neck, round pupils.",
                "तपकिरी रंग, शरीरावर पांढरे-पिवळसर पट्टे, मानेवर पांढरा पट्टा आणि गोलाकार बाहुल्या.",
                "Old stone walls, crevices, ceilings, farm sheds, and brick piles.",
                "घरातील भिंतींच्या फटी, कौले, लाकडांचे ढीग आणि गोठे.",
                "House geckos, skinks, and small lizards.",
                "घरातील पाली, सरडे आणि लहान उंदीर.",
                "Do not panic if found indoors. Gently allow it to exit outside. Harmless.",
                "घरात आढळल्यास घाबरू नका, तो पाली खाण्यासाठी येतो. त्याला बाहेर पडू द्या.",
                "Non-venomous. Wash bite with soap and water.",
                "बिनविषारी. चावल्यास साबणाने स्वच्छ धुवून मलम लावा.",
                R.drawable.yellow_spotted_wolf_snake,
                0
        ));

        // 12. Common Trinket Snake (तस्कर)
        list.add(new DataClass(
                "Common Trinket Snake",
                "तस्कर",
                "Coelognathus helena",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A striking non-venomous snake with geometric markings and distinct black eye stripes.",
                "अत्यंत सुंदर नक्षी असलेला चपळ बिनविषारी साप. डोळ्यांमागे काळे पट्टे असतात.",
                "Tan-brown body with white-and-black crossbars and dark spots, two parallel black stripes behind eyes.",
                "तपकिरी रंग, अंगावर काळे-पांढरे ठिपके आणि जाळीदार नक्षी, डोळ्यांच्या मागे दोन काळे पट्टे.",
                "Agricultural fields, grasslands, termite mounds, gardens, and scrub edges.",
                "शेतातील गवताळ भाग, उंदरांची बिळे, वारुळे आणि बागा.",
                "Rodents, frogs, lizards, and small birds.",
                "लहान उंदीर, सरडे, बेडूक आणि पक्षी.",
                "Puffing of neck is defensive bluff. Completely harmless.",
                "मान फुगवणे हा त्याचा केवळ बचावाचा आव असतो. तो बिनविषारी आहे.",
                "Clean with standard antiseptic. Non-venomous.",
                "जखम स्वच्छ पाण्याने धुवावी.",
                R.drawable.common_trinket_snake,
                0
        ));

        // 13. Montane Trinket Snake (पर्वतीय तस्कर)
        list.add(new DataClass(
                "Montane Trinket Snake",
                "पर्वतीय तस्कर",
                "Coelognathus helena monticollaris",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A mountain subspecies of the Trinket snake found in higher altitudes and plateaus.",
                "तस्कर सापाची डोंगराळ आणि पठारी भागात आढळणारी आकर्षक बिनविषारी उपप्रजाती.",
                "Olive-brown body with prominent black lateral stripes that become solid towards tail.",
                "ऑलिव्ह-तपकिरी रंग, शरीराच्या बाजूने जाणाऱ्या गडद काळ्या रेषा.",
                "Hilly farmlands, forest edges, and elevated scrub plateaus.",
                "डोंगराळ शेती, जंगलाच्या कडा आणि पठारी भाग.",
                "Small rodents, skinks, and lizards.",
                "लहान उंदीर, सरडे आणि पाली.",
                "Harmless species that controls agricultural pests in upland regions.",
                "पठारी भागातील पिकांचे उंदरांपासून रक्षण करणारा निरुपद्रवी साप.",
                "Wash with antiseptic and water.",
                "साबणाने स्वच्छ धुवावे.",
                R.drawable.montain_trinket_snake,
                0
        ));

        // 14. Banded Racer (पट्टेरी धावडा)
        list.add(new DataClass(
                "Banded Racer",
                "पट्टेरी धावडा",
                "Argyrogena fasciolata",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A slender and fast-moving non-venomous snake found in open plains and agricultural belts.",
                "उघड्या माळरानावर आणि शेतात आढळणारा अत्यंत वेगाने पळणारा सडपातळ बिनविषारी साप.",
                "Light brown or olive body with distinct dark narrow cross-bands across the front half.",
                "फिकट तपकिरी किंवा करडा रंग, शरीराच्या पुढच्या भागावर बारीक पांढरे-काळे पट्टे.",
                "Dry grasslands, crop borders, open fields, and rocky scrub.",
                "गवताळ कुरणे, शेतांचे बांध आणि झुडपे.",
                "Frogs, toads, lizards, rodents, and grasshoppers.",
                "बेडूक, सरडे, उंदीर आणि शेतातील कीटक.",
                "It flees at high speed when approached. Harmless.",
                "माणसाची चाहूल लागताच वेगाने पळून जातो. पूर्णपणे निरुपद्रवी.",
                "Antiseptic cleaning is sufficient.",
                "साध्या मलमपट्टीने जखम बरी होते.",
                R.drawable.banded_racer,
                0
        ));

        // 15. Slender Racer (बारीक धावडा)
        list.add(new DataClass(
                "Slender Racer",
                "बारीक धावडा",
                "Platyceps ventromaculatus",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A very thin, harmless racer snake well-adapted to semi-arid climate and scrub fields.",
                "महाराष्ट्रातील कोरड्या भागात आढळणारा अत्यंत बारीक आणि चपळ बिनविषारी साप.",
                "Greyish-buff body with small dark spots along the spine and oblique streak under eye.",
                "करडा-तपकिरी रंग, पाठीवर बारीक काळे ठिपके, डोळ्याखाली काळी तिरपी रेषा.",
                "Dry farmland boundaries, thorny bushes, and fallow fields.",
                "कोरडी शेतजमीन, काटेरी झुडपे आणि पडीक माळरान.",
                "Lizards, small geckos, and grasshoppers.",
                "पाली, सरडे आणि कीटक.",
                "Helps keep pest insect and lizard numbers in check. Non-venomous.",
                "शेतातील कीटक नियंत्रणात ठेवतो.",
                "Standard wound cleaning with water.",
                "जखम पाण्याने धुवावी.",
                R.drawable.slender_racer,
                0
        ));

        // 16. Russell's Kukri Snake (कुखरी)
        list.add(new DataClass(
                "Russell's Kukri Snake",
                "कुखरी साप",
                "Oligodon taeniolatus",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A small harmless snake named after rear teeth curved like a Gurkha kukri knife.",
                "गुरख्यांच्या वक्र कुखरी सुऱ्यासारखे मागचे दात असलेला लहान बिनविषारी साप.",
                "Tan-brown body with dark cross-bars and distinctive dark chevron mark on top of head.",
                "तपकिरी रंग, पाठीवर काळे-पांढरे पट्टे आणि डोक्यावर इंग्रजी V आकाराची खूण.",
                "Rock crevices, termite mounds, stone walls, and dry leaf litter in orchards.",
                "दगडांच्या फटी, वारुळे, विटांचे ढीग आणि बागांमधील पालापाचोळा.",
                "Reptile eggs, small geckos, and insects.",
                "पाली-सरड्यांची अंडी, लहान सरडे आणि कीटक.",
                "Often mistaken for venomous species. Completely harmless to humans.",
                "डोक्यावरील खुणेमुळे गैरसमज होतो, पण तो पूर्णपणे निरुपद्रवी आहे.",
                "Wash with water. Non-venomous.",
                "पाण्याने स्वच्छ धुवावे.",
                R.drawable.russells_kukri_snake,
                0
        ));

        // 17. Bronzeback Tree Snake (रुकई)
        list.add(new DataClass(
                "Bronzeback Tree Snake",
                "रुकई",
                "Dendrelaphis tristis",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A graceful arboreal snake with a striking bronze stripe along its back.",
                "झाडांच्या शेंड्यांवर वावरणारा अतिशय चपळ व देखणा बिनविषारी साप.",
                "Bronze-brown back with dark lateral stripes, large golden eyes, yellowish throat.",
                "पाठीवर तांबूस-पितळी पट्टा, बाजूला काळ्या रेषा आणि मोठे चमकदार डोळे.",
                "Trees, shrubs, orchards, garden vines, and forest edges.",
                "फळबागा, शेतातील मोठी झाडे, वेली आणि झुडपे.",
                "Tree frogs, geckos, chameleons, and small birds.",
                "झाडांवरील बेडूक, सरडे आणि पक्ष्यांची अंडी.",
                "Beneficial in fruit orchards. Completely harmless to humans.",
                "फळबागांमध्ये उपयुक्त. माणसाला कसलाही धोका नाही.",
                "Clean with soap and water.",
                "स्वच्छ पाण्याने धुवावे.",
                R.drawable.common_bronzeback_tree_snake,
                0
        ));

        // 18. Striped Keelback (नानटी)
        list.add(new DataClass(
                "Striped Keelback",
                "नानटी",
                "Amphiesma stolatum",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A gentle harmless snake recognized by two distinct bright yellow stripes along its back.",
                "अत्यंत गरीब आणि शांत स्वभावाचा बिनविषारी साप. शरीरावर दोन पिवळे पट्टे असतात.",
                "Olive-brown body with two prominent parallel yellow stripes running along the back.",
                "तपकिरी रंग, पाठीवर दोन समांतर पिवळे पट्टे आणि खडबडीत खवले.",
                "Moist grass, paddy fields, irrigation channels, and gardens during monsoon.",
                "पावसाळ्यात ओलसर गवत, भाताची शेते, पाण्याच्या चारी आणि अंगण.",
                "Frogs, small toads, and insects.",
                "लहान बेडूक आणि कीटक.",
                "Gentle and safe around children and farmers. Never harm this snake.",
                "हा साप पूर्णपणे निरुपद्रवी आहे. याला कधीही मारू नये.",
                "Wash with water. Non-venomous.",
                "पाण्याने धुवावे.",
                R.drawable.striped_keelback,
                0
        ));

        // 19. Green Keelback (हिरवा दिवड)
        list.add(new DataClass(
                "Green Keelback",
                "हिरवा दिवड",
                "Rhabdophis plumbicolor",
                "Non-Venomous",
                "बिनविषारी",
                "None",
                "बिनविषारी",
                "A stout harmless grass snake with rich green coloring and round pupils.",
                "जाडजुड शरीराचा, गडद हिरव्या रंगाचा बिनविषारी गवताळ साप.",
                "Uniform grass-green color, heavily keeled rough scales, stout body, round pupils.",
                "गडद गवत-हिरवा रंग, खडबडीत खवले, जाड शरीर आणि गोल डोळ्यांच्या बाहुल्या.",
                "Grasslands, crop fields, gardens, and near freshwater during monsoon.",
                "पावसाळ्यातील हिरवे गवत, बागा आणि शेतातील ओलसर जागा.",
                "Toads, frogs, and small rodents.",
                "मोठे बेडूक आणि लहान उंदीर.",
                "Has round pupils and an oval head. Completely harmless.",
                "याचे डोके अंडाकृती आणि बाहुल्या गोल असतात. निरुपद्रवी साप.",
                "Non-venomous. Clean with soap.",
                "साबणाने स्वच्छ धुवावे.",
                R.drawable.green_keelback,
                0
        ));

        // 20. Indian Cat Snake (मांजर डोळ्या साप)
        list.add(new DataClass(
                "Indian Cat Snake",
                "मांजर डोळ्या साप",
                "Boiga trigonata",
                "Mildly Venomous",
                "निम-विषारी",
                "Mild Venom",
                "स्थानिक सौम्य वेदना",
                "A nocturnal rear-fanged snake named for its large cat-like eyes with vertical pupils.",
                "मांजरासारख्या उभ्या बाहुल्यांचे मोठे डोळे असलेला निशाचर साप.",
                "Light brown body with white-and-dark Y-shaped markings along spine, cat-like vertical pupils.",
                "फिकट तपकिरी रंग, पाठीवर पांढऱ्या-काळ्या Y आकाराच्या खुणा आणि मांजरासारख्या उभ्या बाहुल्या.",
                "Bushes, thorny scrub, agricultural fences, bird nests, and tree hollows.",
                "काटेरी झुडपे, शेतातील कुंपण, झाडांच्या ढोल्या आणि पक्ष्यांची घरटी.",
                "Lizards, geckos, small birds, and mice.",
                "पाली, सरडे, लहान पक्षी आणि उंदीर.",
                "Its mild venom is for small lizards. Harmless to humans.",
                "त्याचे विष केवळ लहान सरड्यांसाठी असते, मानवाला धोका नसतो.",
                "Wash with soap and apply antiseptic.",
                "साबणाने स्वच्छ धुवावे.",
                R.drawable.common_cat_snake,
                2
        ));

        return list;
    }
}
