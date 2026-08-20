package com.example.Shetkari_Mitra;

import java.util.ArrayList;
import java.util.List;

public class SnakeDataProvider {

    public static List<DataClass> getSnakeDataList() {
        List<DataClass> list = new ArrayList<>();

        // 1. Indian Cobra / Spectacled Cobra (नाग) - Big Four
        list.add(new DataClass(
                "Spectacled Cobra",
                "नाग (स्पेक्टॅकल कोब्रा)",
                "Naja naja",
                "Venomous (Big Four)",
                "विषारी (प्रमुख चार)",
                "Neurotoxic Venom (हवेतील मज्जासंस्थेवर आघात)",
                "मज्जासंस्थेवर आघात (Neurotoxic Venom)",
                "The Indian Cobra is one of India's 'Big Four' highly venomous snakes. When threatened, it raises the anterior third of its body and spreads its iconic hood featuring a spectacle-shaped mark on the rear.",
                "नाग हा भारतातील 'प्रमुख चार' अतिविषारी सापांपैकी एक आहे. धोका जाणवल्यास तो शरीराचा पुढील एक तृतीयांश भाग वर उचलून फणा काढतो. फण्याच्या पाठीमागे चष्म्यासारखी विशिष्ट खूण असते.",
                "Distinct expandable hood with 'U' or 'O' spectacle mark, smooth scales, color ranges from yellow-brown to blackish. Round eye pupils.",
                "विशाल फणा, फण्यामागे चष्म्याची खूण, गुळगुळीत खवले, पिवळसर-तपकिरी ते काळा रंग, गोलाकार डोळ्यांची बाहुली.",
                "Agricultural fields, grain storage sheds, rat burrows, rock piles, and near human habitations across Maharashtra.",
                "शेतातील बांध, धान्याची कोठारे, उंदरांची बिळे, दगडी ढिगारे आणि वस्त्यांच्या आसपास सर्वत्र आढळतो.",
                "Rats, mice, toads, frogs, birds, and smaller snakes.",
                "उंदीर, घुशी, बेडूक, पक्षी आणि लहान साप.",
                "Never tease or try to catch a hooded cobra. In fields, make vibrations with a stick before stepping into thick crops. Wear gumboots.",
                "फणा काढलेल्या नागाला कधीही त्रास देऊ नका किंवा पकडण्याचा प्रयत्न करू नका. शेतात जाताना काठी जमिनीवर आपटून कंपन निर्माण करा.",
                "Immediate immobilization of the bitten limb. Rush to the nearest Anti-Snake Venom (ASV) equipped hospital within the Golden Hour. Do NOT apply tourniquet.",
                "दंश झालेला अवयव अजिबात हलवू नका. ताबडतोब ASV उपलब्ध असलेल्या जवळच्या शासकीय किंवा खाजगी रुग्णालयात पोहोचवा. दोरी बांधू नका.",
                R.drawable.p2,
                1
        ));

        // 2. Common Krait (मण्यार) - Big Four
        list.add(new DataClass(
                "Common Krait",
                "मण्यार (कंडी मण्यार)",
                "Bungarus caeruleus",
                "Venomous (Big Four)",
                "विषारी (प्रमुख चार - अतिविषारी)",
                "Potent Neurotoxic Venom (तीव्र मज्जासंस्थेवर आघात)",
                "तीव्र मज्जासंस्थेवर आघात (Potent Neurotoxic)",
                "The Common Krait possesses the most potent venom among Indian land snakes. It is strictly nocturnal and responsible for severe nighttime snakebites while victims sleep on the floor.",
                "मण्यार हा जमिनीवरील सापांमध्ये सर्वात तीव्र विष असलेला साप आहे. तो पूर्णपणे निशाचर (रात्री फिरणारा) असून जमिनीवर झोपलेल्या व्यक्तींना नकळत चावल्याने गंभीर घटना घडतात.",
                "Glossy blue-black or shiny dark body with narrow white cross-bands (paired) starting after the neck. Hexagonal scales along the middle of the back.",
                "चमकदार निळसर-काळा रंग, मानेनंतर शरीरावर पांढरे दुहेरी पट्टे, पाठीच्या मध्यभागी षटकोनी आकाराचे मोठे खवले.",
                "Villages, mud houses, crop fields, piles of firewood, brick kilns, and rodent burrows throughout Jalna and Maharashtra.",
                "ग्रामीण वस्त्या, मातीची घरे, गवताचे ढीग, लाकडांचे ढिगारे आणि विटांच्या भट्ट्यांमध्ये वास्तव्य.",
                "Exclusively feeds on other snakes (ophiophagous), small rodents, lizards, and frogs.",
                "इतर लहान साप (धामण, दिवड), लहान उंदीर, सरडे आणि बेडूक.",
                "Always sleep on a raised bed/cot and use mosquito nets in farm houses. Do not sleep on the floor during monsoon.",
                "शेतवस्तीवर किंवा घरात नेहमी जमिनीपासून वर खाटेवर झोपावे आणि मच्छरदाणी वापरावी. जमिनीवर झोपणे टाळावे.",
                "Victim often feels no local pain or swelling. Watch for eyelid drooping, abdominal pain, and breathing difficulty. Administer ASV immediately.",
                "दंशाच्या ठिकाणी सहसा वेदना किंवा सूज जाणवत नाही. पोटदुखी, पापण्या जड होणे किंवा श्वास घेण्यास त्रास झाल्यास तातडीने ASV द्यावे.",
                R.drawable.common_krait,
                1
        ));

        // 3. Russell's Viper (घोणस) - Big Four
        list.add(new DataClass(
                "Russell's Viper",
                "घोणस (रसेल वायपर)",
                "Daboia russelii",
                "Venomous (Big Four)",
                "विषारी (प्रमुख चार)",
                "Hemotoxic & Cytotoxic Venom (रक्ताभिसरण व ऊती नासणारे विष)",
                "रक्ताभिसरण व ऊतींवर आघात (Hemotoxic & Cytotoxic)",
                "Russell's Viper is responsible for the highest number of venomous snakebites in Maharashtra. It produces an extremely loud, pressure-cooker like warning hiss when cornered.",
                "महाराष्ट्रात सर्वाधिक सर्पदंशाच्या घटनांसाठी घोणस जबाबदार आहे. धोका जाणवल्यास तो कुकरच्या शिट्टीसारखा प्रचंड मोठा फुत्कार सोडतो.",
                "Robust, heavy body with three longitudinal rows of dark brown, chain-like oval spots with white edges on a tan/yellowish background. Triangular flattened head.",
                "जाडजुड शरीर, त्रिकोणी चपटे डोके, पाठीवर साखळीसारखे तीन ओळीत अंडाकृती तपकिरी डाग, मानेवर बारीक खवले आणि उभ्या बाहुल्या.",
                "Sugarcane fields, cotton farms, scrublands, grassland borders, and rock piles near moisture.",
                "ऊस, कपाशी, सोयाबीनची शेते, गवताळ भाग आणि पाणथळ जागेजवळील दगडधोंडे.",
                "Primarily rodents (rats, mice), small birds, and lizards.",
                "प्रामुख्याने शेतातील उंदीर, घुशी आणि लहान पक्षी.",
                "Watch your step while harvesting sugarcane or weeding crops. Use a stick and torch at all times. Do not attempt to catch it.",
                "ऊस तोडणी व शेतात खुरपणी करताना काळजीपूर्वक पावले टाका. रात्री नेहमी विजेरी (टॉर्च) वापरा.",
                "Causes severe local swelling, blistering, and internal bleeding. Rush to hospital for anti-venom. Keep patient calm.",
                "दंशानंतर प्रचंड सूज, वेदना व रक्तस्त्राव होतो. तात्काळ अँटी-व्हेनम उपचारासाठी जिल्हा रुग्णालयात हलवा.",
                R.drawable.viper,
                1
        ));

        // 4. Saw-Scaled Viper (फुरसे) - Big Four
        list.add(new DataClass(
                "Saw-Scaled Viper",
                "फुरसे (रती फुरसे)",
                "Echis carinatus",
                "Venomous (Big Four)",
                "विषारी (प्रमुख चार)",
                "Potent Hemotoxic Venom (रक्तस्त्राव घडवणारे विष)",
                "रक्तस्त्राव घडवणारे विष (Potent Hemotoxic)",
                "A small but highly irritable and venomous viper. When threatened, it coils into an '8' shape and rubs its serrated lateral scales together, producing a rasping saw-like sound.",
                "हा आकाराने लहान पण अत्यंत चपळ आणि विषारी साप आहे. धोका वाटल्यास तो शरीराची ८ सारखी घडी घालून अंगावरील खडबडीत खवले एकमेकांवर घासून करवतीसारखा आवाज करतो.",
                "Small size (30-60 cm), bird-foot mark on head, keeled serrated scales, undulating wavy side line with light spots.",
                "लहान आकार (३० ते ६० सेमी), डोक्यावर पांढऱ्या त्रिशूल किंवा पक्ष्यांच्या पायासारखी खूण, खडबडीत खवले, अंगावर पांढरे नागमोडी डाग.",
                "Dry rocky terrain, arid agricultural fields, gravel beds, and thorn scrub forests across central Maharashtra.",
                "खडकाळ माळरान, कोरडी शेते, दगडांचे ढिगारे आणि झुडपी जंगलात आढळतो.",
                "Scorpions, centipedes, lizards, geckos, frogs, and small mice.",
                "विंचू, गोम, सरडे, पाल, लहान उंदीर आणि किडे.",
                "Never put hands under loose rocks or dry bushes without looking. Wear closed shoes or boots when walking on rocky ground.",
                "माळरानावरील किंवा शेतातील दगड विनाकारण हाताने उचलू नका. काम करताना पायात बंद बूट किंवा पायताण वापरा.",
                "Bite causes non-clotting blood (bleeding from gums/wound). Requires specific polyvalent ASV infusion at hospital.",
                "दंशामुळे रक्ताची गुठळी होत नाही व हिरड्यांमधून किंवा जखमेतून रक्त वाहते. त्वरित रुग्णालयात ASV देणे गरजेचे असते.",
                R.drawable.saw_scaled_viper,
                1
        ));

        // 5. Bamboo Pit Viper (चापडा / हिरवा चापडा)
        list.add(new DataClass(
                "Bamboo Pit Viper",
                "हिरवा चापडा (चापडा)",
                "Craspedocephalus gramineus",
                "Venomous (Arboreal)",
                "विषारी (वृक्षवासी)",
                "Hemotoxic Venom (स्थानिक सूज व वेदना)",
                "स्थानिक सूज व वेदना (Hemotoxic Venom)",
                "An arboreal, bright green venomous pit viper with specialized heat-sensing pits between the eyes and nostrils for hunting in darkness.",
                "झाडांवर राहणारा चमकदार हिरवा विषारी साप. डोळे आणि नाकपुडीच्या मध्ये उष्णता ओळखणारे विशेष छिद्र (Pit) असते, ज्याद्वारे तो अंधारात शिकार शोधतो.",
                "Bright green body, yellowish or white belly, triangular head, prehensile tail with reddish-brown tip, vertical pupils.",
                "चमकदार पोपटी-हिरवा रंग, पिवळसर पोट, स्पष्ट त्रिकोणी डोके, पकड घेणारी तांबूस शेपुट आणि मांजरासारख्या उभ्या बाहुल्या.",
                "Bamboo thickets, fruit orchards, dense shrubs, stream vegetation, and Western Ghats forests.",
                "बांबूची वने, फळबागा, दाट झुडपे आणि पाण्याच्या प्रवाहाजवळील झाडेझुडपे.",
                "Frogs, tree toads, small birds, and geckos.",
                "बेडूक, झाडांवरील पक्षी, पाली आणि सरडे.",
                "Look closely before picking fruits or trimming tree branches. Bamboo pit vipers remain completely still disguised as foliage.",
                "फळांची तोडणी करताना किंवा झाडांच्या फांद्या छाटताना काळजीपूर्वक पहा. तो पानांमध्ये हुबेहूब मिसळून राहतो.",
                "Bites cause severe swelling and pain. Hospitalization and symptom-based medical care/ASV required.",
                "चावल्यास तीव्र सूज व वेदना होतात. रुग्णाला तत्काळ वैद्यकीय देखरेखीखाली रुग्णालयात न्यावे.",
                R.drawable.bamboo_pit_viper,
                1
        ));

        // 6. Indian Rat Snake (धामण) - Farmer's Best Friend
        list.add(new DataClass(
                "Indian Rat Snake",
                "धामण (शेतकऱ्यांचा मित्र)",
                "Ptyas mucosa",
                "Non-Venomous (Harmless)",
                "बिनविषारी (शेतकऱ्यांचा खरा मित्र)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "The Indian Rat Snake is a large, powerful, and exceptionally fast non-venomous snake. It is considered the farmer's greatest natural ally because it consumes thousands of crop-destroying rats.",
                "धामण हा अत्यंत वेगवान, लांब आणि शक्तिशाली बिनविषारी साप आहे. तो शेतातील लाखो रुपयांचे पीक उद्ध्वस्त करणाऱ्या उंदरांचा फडशा पाडतो, म्हणून तो शेतकऱ्यांचा खरा मित्र मानला जातो.",
                "Long, slender body (up to 8-10 feet), yellow-olive to brown color, large round eyes, black vertical bars along the upper lip scales.",
                "लांब चपळ शरीर (६ ते ९ फूट), पिवळसर-तपकिरी रंग, मोठे डोळे आणि ओठांच्या खवल्यांवर काळ्या उभ्या रेषांची स्पष्ट खूण.",
                "Agricultural fields, crop sheds, granaries, wells, trees, and grasslands throughout Maharashtra.",
                "शेतातील पिके, गोठे, धान्याची कोठारे, विहिरी आणि बांधांवर सर्वत्र आढळतो.",
                "Chiefly agricultural rodents (rats, mice), frogs, toads, and lizards.",
                "प्रामुख्याने शेतातील उंदीर, घुशी, बेडूक आणि सरडे.",
                "Never kill a rat snake! It cannot harm humans and protects your grains from rodents. It often escapes quickly if left alone.",
                "धामण सापाला चुकूनही मारू नका! तो माणसाला कोणताही धोका पोहोचवत नाही आणि पिकांचे उंदरांपासून रक्षण करतो.",
                "Non-venomous bite. Wash with clean soap and water. Antiseptic application is sufficient; ASV is NOT needed.",
                "दंश पूर्णपणे बिनविषारी असतो. जखम साबणाने स्वच्छ धुवून मलम लावावा. अँटी-व्हेनमची गरज नसते.",
                R.drawable.ratsnake,
                0
        ));

        // 7. Indian Rock Python (भारतीय अजगर)
        list.add(new DataClass(
                "Indian Rock Python",
                "भारतीय अजगर",
                "Python molurus",
                "Non-Venomous (Protected)",
                "बिनविषारी (वन्यजीव संरक्षण अनुसूची-१)",
                "None (Constrictor)",
                "काहीही नाही (वेटोळे घालून शिकार करणारा)",
                "A magnificent, heavy-bodied non-venomous constrictor snake protected under Schedule I of the Wildlife Protection Act. It kills prey through constriction, not venom.",
                "भारतीय अजगर हा अत्यंत वजनदार, शांत आणि बिनविषारी साप आहे. तो वन्यजीव संरक्षण कायद्यान्वये संरक्षित आहे. तो शिकार वेटोळ्याने आवळून मारतो, त्याला विष नसते.",
                "Heavy patterned body with yellowish/tan blotches outlined in dark brown, lance-shaped mark on head, heat sensory pits on upper lip.",
                "मोठे जाडजुड शरीर, अंगावर सुंदर पिवळसर-तपकिरी ठिपके, डोक्यावर बाणाच्या आकाराची खूण आणि वरच्या ओठावर संवेदी खड्डे.",
                "Rocky hillsides, riverbanks, forest edges, scrublands, and near agricultural water canals.",
                "नद्या-ओढ्यांचे काठ, खडकाळ गुहा, शेतांच्या सीमा आणि पाण्याचे कालवे.",
                "Mammals, rodents, birds, hares, and monitor lizards.",
                "उंदीर, ससे, लहान सस्तन प्राणी, पक्षी आणि घोरपड.",
                "Pythons are slow-moving and non-aggressive unless provoked. Do not harass. Call a certified Sarpmitra if found near farms.",
                "अजगर अत्यंत शांत असतो. त्याला त्रास देऊ नका. शेतात किंवा विहिरीजवळ आढळल्यास सर्पमित्राला बोलावून जंगलात सोडून द्यावे.",
                "Non-venomous. Bites can cause lacerations; clean with antiseptic and take a tetanus shot if needed.",
                "बिनविषारी. चावल्यास दातांची जखम स्वच्छ धुवावी व धनुर्वाताचे (Tetanus) इंजेक्शन घ्यावे.",
                R.drawable.indian_rock_python,
                0
        ));

        // 8. Checkered Keelback (दिवड / विरुळा)
        list.add(new DataClass(
                "Checkered Keelback",
                "दिवड / विरुळा (पाण्यातील साप)",
                "Fowlea piscator",
                "Non-Venomous (Semi-Aquatic)",
                "बिनविषारी (जलचर)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A highly active semi-aquatic non-venomous snake found in every well, farm pond, and irrigation channel. Often puffs its neck when agitated.",
                "शेतातील विहिरी, शेततळे आणि पाटबंधाऱ्यांमध्ये आढळणारा अत्यंत चपळ बिनविषारी साप. चिडल्यावर तो आपली मान पसरवून फुगवतो.",
                "Olive-brown or yellowish body covered with a black checkered grid pattern, keeled scales, two black streaks behind each eye.",
                "तपकिरी-पिवळसर रंग, शरीरावर काळ्या चौकटींची (बुद्धिबळासारखी) जाळीदार रचना, डोळ्यांमागे दोन काळे पट्टे.",
                "Wells, farm ponds (शेततळे), rivers, paddy fields, and irrigation streams across Jalna district.",
                "विहिरी, शेततळी, भातशेती, कालवे आणि ओहोळ.",
                "Fish, tadpoles, frogs, and aquatic insects.",
                "मासे, बेडूक, डिंभक (Tadpoles) आणि जलचर कीटक.",
                "Harmless to farmers and cattle while working in water. Do not harm when clearing irrigation pump areas.",
                "पाण्यात काम करताना शेतकऱ्यांसाठी पूर्णपणे निरुपद्रवी. विहिरी किंवा मोटार पंप दुरुस्त करताना मारू नका.",
                "Wash bite wound with soap and water. Completely non-venomous.",
                "जखम स्वच्छ पाण्याने व साबणाने धुवावी. विषबाधा होत नाही.",
                R.drawable.checkered_snake,
                0
        ));

        // 9. Red Sand Boa (मांडोळ / दुतोंडी साप)
        list.add(new DataClass(
                "Red Sand Boa",
                "मांडोळ (दुतोंडी साप)",
                "Eryx johnii",
                "Non-Venomous (Protected)",
                "बिनविषारी (संरक्षित - अंधश्रद्धेला बळी)",
                "None (Burrowing Constrictor)",
                "काहीही नाही (बिळात राहणारा निरुपद्रवी)",
                "A gentle, thick-bodied burrowing non-venomous snake. Its blunt tail resembles its head, giving rise to the myth that it has two heads. Heavily protected against illegal poaching.",
                "अत्यंत शांत स्वभावाचा, जमिनीत बिळे करून राहणारा बिनविषारी साप. त्याची शेपूट डोक्यासारखीच बोथट असल्याने त्याला 'दुतोंडी' म्हणतात. अंधश्रद्धेमुळे या सापाची तस्करी होते, जो कायदेशीर गुन्हा आहे.",
                "Thick sausage-like body, reddish-brown to dark tan, blunt rounded tail mimicking head, very small eyes.",
                "जाड दंडगोलाकार शरीर, तांबूस-तपकिरी रंग, डोक्यासारखीच बोथट शेपूट, बारीक डोळे आणि गुळगुळीत खवले.",
                "Loose sandy soil, agricultural fields, arid scrublands, and rodent burrows.",
                "भुसभुशीत वालुकामय जमीन, उंदरांची बिळे आणि शेतातील माती.",
                "Rodents, mice, and small burrowing creatures.",
                "उंदीर, घुशी आणि लहान किडे.",
                "It is a myth that sand boas bring wealth or have two heads. Sand boas maintain ecological balance by eating agricultural pests.",
                "मांडोळ सापामुळे धनलाभ होतो किंवा त्याला दोन तोंडे असतात हा निव्वळ गैरसमज आहे. अंधश्रद्धेला बळी पडू नका आणि तस्करी रोखा.",
                "Harmless bite. Clean the wound with antiseptic soap.",
                "निरुपद्रवी. जखम साबणाने स्वच्छ धुवावी.",
                R.drawable.red_sand_boa,
                0
        ));

        // 10. Green Vine Snake (हरणटोळ)
        list.add(new DataClass(
                "Green Vine Snake",
                "हरणटोळ (हिरवा झाडावरील साप)",
                "Ahaetulla nasuta",
                "Mildly Venomous (Harmless)",
                "निम-विषारी (मानवाला धोका नाही)",
                "Mild Opisthoglyphous Venom (स्थानिक सौम्य चुरचूर)",
                "स्थानिक सौम्य चुरचूर (Mild Opisthoglyphous)",
                "An extremely slender, arboreal snake that camouflages perfectly among green leaves. It has a long pointed snout and unusual horizontal keyhole-shaped pupils.",
                "झाडांच्या फांद्यांमध्ये हुबेहूब लपणारा अत्यंत सडपातळ हिरवा साप. त्याचे तोंड लांबट टोकदार असते आणि डोळ्यांच्या बाहुल्या आडव्या चावीच्या छिद्रासारख्या असतात.",
                "Bright fluorescent green, elongated pointed snout, slender body, horizontal keyhole pupils, white/yellow ventro-lateral line.",
                "पोपटी हिरवा रंग, लांब टोकदार चोच, अतिशय सडपातळ अंग, डोळ्यात आडवी बाहुली आणि अंगावर बारीक पांढरी रेषा.",
                "Fruit trees, bushes, garden hedges, grape/pomegranate orchards, and Western Ghats forests.",
                "द्राक्ष व डाळिंबाच्या बागा, झाडांच्या फांद्या, झुडपे आणि जंगले.",
                "Lizards, geckos, frogs, and small birds.",
                "पाली, सरडे, झाडांवरील बेडूक आणि लहान पक्षी.",
                "Myth: It does NOT pierce human eyes or jump from trees. Harmless to humans and beneficial in orchards.",
                "गैरसमज: हा डोळे फोडतो हा पूर्णपणे खोटा समज आहे. हरणटोळ डोळे फोडत नाही व मानवाला कसलाही धोका नसतो.",
                "Mild bite causes slight itching or temporary swelling at the site. No anti-venom required.",
                "चावल्यास थोडी खाज किंवा हलकी सूज येऊ शकते. अँटी-व्हेनमची अजिबात गरज नसते.",
                R.drawable.green_vine_snake,
                2
        ));

        // 11. Common Wolf Snake (कवड्या)
        list.add(new DataClass(
                "Common Wolf Snake",
                "कवड्या (घरातील साप)",
                "Lycodon aulicus",
                "Non-Venomous (Krait Mimic)",
                "बिनविषारी (मण्यार सारखा दिसणारा)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A small, agile non-venomous snake frequently found inside rural homes hunting geckos. It closely mimics the deadly Common Krait for defense.",
                "घरांच्या भिंती, कौले आणि गोठ्यांमध्ये पालींच्या शिकारीसाठी आढळणारा लहान बिनविषारी साप. संरक्षणासाठी तो विषारी मण्यार सापाची नक्कल करतो.",
                "Brownish body with white/yellowish crossbars that expand on the sides, collar band on neck, round pupils, and prominent teeth for gripping geckos.",
                "तपकिरी रंग, शरीरावर पांढरे-पिवळसर पट्टे जे बाजूला रुंद होतात, मानेवर पांढरा पट्टा, गोलाकार बाहुल्या (मण्यारमध्ये उभ्या खवले असतात).",
                "Old stone walls, crevices, ceilings, farm sheds, and brick piles.",
                "घरातील भिंतींच्या फटी, कौले, लाकडांचे ढीग आणि गोठे.",
                "House geckos, skinks, and small lizards.",
                "घरातील पाली, सरडे आणि लहान उंदीर.",
                "Do not panic if found indoors. Gently allow it to exit outside. Harmless to humans and livestock.",
                "घरात आढळल्यास घाबरू नका, तो पाली खाण्यासाठी येतो. त्याला सुरक्षितपणे बाहेर पडू द्या.",
                "Non-venomous. Wash bite with soap and water.",
                "बिनविषारी. चावल्यास साबणाने स्वच्छ धुवून मलम लावा.",
                R.drawable.yellow_spotted_wolf_snake,
                0
        ));

        // 12. Common Trinket Snake (तस्कर)
        list.add(new DataClass(
                "Common Trinket Snake",
                "तस्कर (तस्कर साप)",
                "Coelognathus helena",
                "Non-Venomous (Active)",
                "बिनविषारी (लहान शिकारी)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A striking, non-venomous constrictor snake with beautiful geometric markings and distinct black eye stripes. Known to puff its neck vertically when startled.",
                "अत्यंत सुंदर नक्षी असलेला चपळ बिनविषारी साप. डोळ्यांमागे काळे पट्टे असतात. घाबरल्यावर तो शरीराचे पुढील अंग 'S' आकारात उचलून मान फुगवतो.",
                "Tan/brown body with white-and-black crossbars and dark spots, two distinct parallel black stripes behind the eyes, check pattern on neck.",
                "तपकिरी रंग, अंगावर काळे-पांढरे ठिपके आणि जाळीदार नक्षी, डोळ्यांच्या मागे दोन काळे समांतर पट्टे.",
                "Agricultural fields, grasslands, termite mounds, gardens, and scrub edges.",
                "शेतातील गवताळ भाग, उंदरांची बिळे, वारुळे आणि बागा.",
                "Rodents, frogs, lizards, and small birds.",
                "लहान उंदीर, सरडे, बेडूक आणि पक्ष्यांची पिल्ले.",
                "Puffing of the neck is pure bluff behavior. Completely harmless to farmers.",
                "मान फुगवून धावून येणे हा त्याचा केवळ बचावाचा आव असतो. तो बिनविषारी आहे.",
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
                "Non-Venomous (Hill Subspecies)",
                "बिनविषारी (डोंगराळ भाग)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A beautiful mountain subspecies of the Trinket snake found in higher altitudes and plateaus of Maharashtra.",
                "तस्कर सापाची डोंगराळ आणि पठारी भागात आढळणारी आकर्षक बिनविषारी उपप्रजाती.",
                "Olive-brown body with prominent black lateral stripes that become solid towards the tail, dark eye mask.",
                "ऑलिव्ह-तपकिरी रंग, शरीराच्या बाजूने जाणाऱ्या गडद काळ्या रेषा, मानेवर नक्षी.",
                "Hilly farmlands, forest edges, and elevated scrub plateaus.",
                "डोंगराळ शेती, जंगलाच्या कडा आणि पठारी भाग.",
                "Small rodents, skinks, and lizards.",
                "लहान उंदीर, सरडे आणि पाली.",
                "Harmless species that controls agricultural pests in upland regions.",
                "पठारी व डोंगराळ भागातील पिकांचे उंदरांपासून रक्षण करणारा निरुपद्रवी साप.",
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
                "Non-Venomous (Fast Runner)",
                "बिनविषारी (अतिवेगवान)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A slender, graceful, and exceptionally fast-moving non-venomous colubrid snake found in open plains and agricultural belts.",
                "उघड्या माळरानावर आणि शेतात आढळणारा अत्यंत वेगाने पळणारा सडपातळ बिनविषारी साप.",
                "Light brown or olive body with distinct dark narrow cross-bands across the front half that fade towards the rear, large eyes.",
                "फिकट तपकिरी किंवा करडा रंग, शरीराच्या पुढच्या भागावर बारीक पांढरे-काळे पट्टे, मोठे डोळे.",
                "Dry grasslands, crop borders, open fields, and rocky scrub.",
                "गवताळ कुरणे, शेतांचे बांध आणि झुडपे.",
                "Frogs, toads, lizards, rodents, and grasshoppers.",
                "बेडूक, सरडे, उंदीर आणि शेतातील टोळ/कीटक.",
                "It flees at lightning speed when approached. Harmless.",
                "माणसाची चाहूल लागताच विजेच्या वेगाने पळून जातो. पूर्णपणे निरुपद्रवी.",
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
                "Non-Venomous (Arid Grassland)",
                "बिनविषारी (माळरान)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A very thin, harmless racer snake well-adapted to the semi-arid climate and scrub fields of Maharashtra.",
                "महाराष्ट्रातील कोरड्या व निम-शुष्क भागात आढळणारा अत्यंत बारीक आणि चपळ बिनविषारी साप.",
                "Greyish-buff or tan body with small dark spots along the spine, dark oblique streak under the eye.",
                "करडा-तपकिरी रंग, पाठीवर बारीक काळे ठिपके, डोळ्याखाली काळी तिरपी रेषा.",
                "Dry farmland boundaries, thorny bushes, and fallow fields.",
                "कोरडी शेतजमीन, काटेरी झुडपे आणि पडीक माळरान.",
                "Lizards, small geckos, and grasshoppers.",
                "पाली, सरडे आणि कीटक.",
                "Helps keep pest insect and lizard numbers in check. Non-venomous.",
                "शेतातील कीटक नियंत्रणात ठेवतो.",
                "Standard wound cleaning.",
                "जखम पाण्याने धुवावी.",
                R.drawable.slender_racer,
                0
        ));

        // 16. Russell's Kukri Snake (कुखरी)
        list.add(new DataClass(
                "Russell's Kukri Snake",
                "कुखरी साप",
                "Oligodon taeniolatus",
                "Non-Venomous (Harmless)",
                "बिनविषारी (निरुपद्रवी)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A small, harmless snake named after its specialized rear teeth shaped like a curved Gurkha 'Kukri' knife, used to slit bird/reptile eggs.",
                "गुरख्यांच्या वक्र 'कुखरी' सुऱ्यासारखे मागचे दात असलेला लहान बिनविषारी साप. हे दात अंड्यांचे कवच कापण्यासाठी उपयोगी पडतात.",
                "Tan-brown body with dark cross-bars or variegations, distinctive dark chevron mark on top of head pointing backwards.",
                "तपकिरी रंग, पाठीवर काळे-पांढरे पट्टे, डोक्यावर इंग्रजी 'V' आकाराची किंवा बाणासारखी खूण.",
                "Rock crevices, termite mounds, stone walls, and dry leaf litter in orchards.",
                "दगडांच्या फटी, वारुळे, विटांचे ढीग आणि बागांमधील वाळलेला पालापाचोळा.",
                "Reptile eggs, small geckos, and insects.",
                "पाली-सरड्यांची अंडी, लहान सरडे आणि कीटक.",
                "Often mistaken for venomous snakes due to head pattern. Completely harmless to humans.",
                "डोक्यावरील खुणेमुळे काहीवेळा विषारी समजला जातो, पण तो पूर्णपणे निरुपद्रवी आहे.",
                "Wash with water. Non-venomous.",
                "पाण्याने स्वच्छ धुवावे.",
                R.drawable.russells_kukri_snake,
                0
        ));

        // 17. Common Bronzeback Tree Snake (रुकई)
        list.add(new DataClass(
                "Bronzeback Tree Snake",
                "रुकई (झाडावरील चपळ साप)",
                "Dendrelaphis tristis",
                "Non-Venomous (Arboreal)",
                "बिनविषारी (वृक्षवासी)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A slender, graceful arboreal snake with a striking bronze stripe along its back. It moves through tree canopies with incredible agility.",
                "झाडांच्या शेंड्यांवर वावरणारा अतिशय चपळ व देखणा बिनविषारी साप. पाठीवर तांबूस-कांस्य (Bronze) रंगाचा रुंद पट्टा असतो.",
                "Bronze-brown back with dark lateral stripes, large golden eyes, bluish-white belly, yellow throat.",
                "पाठीवर तांबूस-पितळी पट्टा, बाजूला काळ्या रेषा, मोठे चमकदार डोळे आणि पिवळसर गळा.",
                "Trees, shrubs, orchards, garden vines, and forest edges.",
                "फळबागा, शेतातील मोठी झाडे, वेली आणि झुडपे.",
                "Tree frogs, geckos, chameleons, and small birds.",
                "झाडांवरील बेडूक, सरडे, सरडा (Chameleon) आणि पक्ष्यांची अंडी.",
                "Beneficial in fruit orchards. Jumps between branches if disturbed. Non-venomous.",
                "फळबागांमध्ये उपयुक्त. माणसाला कसलाही धोका नाही.",
                "Clean with soap and water.",
                "स्वच्छ पाण्याने धुवावे.",
                R.drawable.common_bronzeback_tree_snake,
                0
        ));

        // 18. Striped Keelback (नानटी / पट्टेरी पिवळा साप)
        list.add(new DataClass(
                "Striped Keelback",
                "नानटी (पट्टेरी पिवळा साप)",
                "Amphiesma stolatum",
                "Non-Venomous (Gentle)",
                "बिनविषारी (अतिशय शांत)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A gentle, harmless snake that never bites even when picked up. Recognized by two distinct bright yellow stripes running down the length of its body.",
                "अत्यंत गरीब आणि शांत स्वभावाचा बिनविषारी साप. पकडला तरी चावत नाही. शरीरावर डोक्यापासून शेपटीपर्यंत दोन पिवळे पट्टे असतात.",
                "Olive-brown body with two prominent parallel yellow/buff stripes running along the back, keeled rough scales, dark cross-bars.",
                "तपकिरी रंग, पाठीवर दोन समांतर पिवळे पट्टे, खडबडीत खवले, डोळ्यांखाली काळ्या उभ्या रेषा.",
                "Moist grass, paddy fields, irrigation channels, and garden soil during monsoon.",
                "पावसाळ्यात ओलसर गवत, भाताची शेते, पाण्याच्या चारी आणि घरासमोरील अंगण.",
                "Frogs, small toads, and insects.",
                "लहान बेडूक आणि कीटक.",
                "100% gentle and safe around children and farmers. Never harm this beneficial snake.",
                "हा साप १००% निरुपद्रवी आहे. याला कधीही मारू नये.",
                "Wash with water. Non-venomous.",
                "पाण्याने धुवावे.",
                R.drawable.striped_keelback,
                0
        ));

        // 19. Green Keelback (हिरवा दिवड / गवताळ साप)
        list.add(new DataClass(
                "Green Keelback",
                "हिरवा दिवड (गवताळ साप)",
                "Rhabdophis plumbicolor",
                "Non-Venomous (Stout Grass Snake)",
                "बिनविषारी (गवताळ साप)",
                "None (Non-Venomous)",
                "काहीही नाही (बिनविषारी)",
                "A stout, heavy-bodied harmless grass snake with rich green coloring. Juveniles have a bright yellow/white collar that disappears with age.",
                "जाडजुड शरीराचा, गडद हिरव्या रंगाचा बिनविषारी गवताळ साप. पिल्लांच्या मानेवर पिवळा किंवा पांढरा 'V' आकाराचा स्पष्ट पट्टा असतो.",
                "Uniform grass-green color, heavily keeled rough scales, stout body, black chevron collar in juveniles.",
                "गडद गवत-हिरवा रंग, खडबडीत खवले, जाड शरीर, पिल्लांच्या मानेवर काळा-पिवळा कॉलरसारखा पट्टा.",
                "Grasslands, crop fields, gardens, and near freshwater during monsoon.",
                "पावसाळ्यातील हिरवे गवत, बागा आणि शेतातील ओलसर जागा.",
                "Toads, frogs, and small rodents.",
                "मोठे बेडूक, भेक आणि उंदीर.",
                "Often mistaken for green pit viper. Green keelback has round pupils and no triangular pit head.",
                "हिरव्या चापड्यासारखा भासतो, परंतु याचे डोके अंडाकृती आणि डोळ्यांच्या बाहुल्या गोल असतात.",
                "Non-venomous. Clean with antiseptic soap.",
                "साबणाने स्वच्छ धुवावे.",
                R.drawable.green_keelback,
                0
        ));

        // 20. Gamma Cat Snake (मांजर डोळ्या साप)
        list.add(new DataClass(
                "Indian Cat Snake",
                "मांजर डोळ्या साप",
                "Boiga trigonata",
                "Mildly Venomous (Rear-fanged)",
                "निम-विषारी (मानवाला निरुपद्रवी)",
                "Mild Opisthoglyphous Venom (स्थानिक वेदना)",
                "स्थानिक सौम्य वेदना (Mild Venom)",
                "A nocturnal, rear-fanged snake named for its large cat-like eyes with vertical slit pupils. It coils in an 'S' shape and strikes defensively when cornered.",
                "मांजरासारख्या उभ्या बाहुल्यांचे मोठे डोळे असलेला निशाचर साप. धोका जाणवल्यास तो इंग्रजी 'S' आकारात वेटोळे घालून बचावात्मक पवित्रा घेतो.",
                "Light brown body with white-and-dark 'Y' or chevron markings along the spine, broad head distinct from neck, cat-like vertical slit pupils.",
                "फिकट तपकिरी रंग, पाठीवर पांढऱ्या-काळ्या 'Y' आकाराच्या खुणा, मानेपेक्षा रुंद डोके आणि मांजरासारख्या उभ्या बाहुल्या.",
                "Bushes, thorny scrub, agricultural fences, bird nests, and tree hollows.",
                "काटेरी झुडपे, शेतातील कुंपण, झाडांच्या ढोल्या आणि पक्ष्यांची घरटी.",
                "Lizards, geckos, small birds, and mice.",
                "पाली, सरडे, लहान पक्षी आणि उंदीर.",
                "Rear fangs cannot effectively inject venom into humans. Completely harmless.",
                "त्याचे विष केवळ लहान सरड्यांसाठी असते, मानवाला कोणताही गंभीर धोका नसतो.",
                "Wash with soap and apply antiseptic.",
                "साबणाने स्वच्छ धुवावे.",
                R.drawable.common_cat_snake,
                2
        ));

        return list;
    }
}
