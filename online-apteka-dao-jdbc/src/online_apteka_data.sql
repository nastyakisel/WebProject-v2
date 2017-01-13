CHARACTER SET utf8;

USE online_apteka;

INSERT INTO Role (ID, ROLE_NAME) VALUES ('1', '������');
INSERT INTO Role (ID, ROLE_NAME) VALUES ('2', '���������');
INSERT INTO Role (ID, ROLE_NAME) VALUES ('3', '����');


INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('ivanov@mail.ru', 'fgdhk34', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('metl@mail.ru', 'dkljhs', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('djoli@mail.ru', 'qwerty', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('timur@mail.ru', 'timur', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('nasta@tut.by', '123456', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('vlad@tut.by', 'fskjyy', '1');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('admin', 'admin', '2');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('petrov@mail.ru', 'dfghyr', '3');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('doc@mail.ru', 'asder', '3');
INSERT INTO User (USER_NAME, PASSWORD, FK_ROLE_ID) VALUES ('nasta', 'nasta', '1');

INSERT INTO User (ID, FIRST_NAME, SECOND_NAME) VALUES (1, 'Dima', 'Ivanov');
INSERT INTO User (ID, FIRST_NAME, SECOND_NAME) VALUES (2, 'Юлия', 'Метлицкая');
INSERT INTO User (ID, FIRST_NAME, SECOND_NAME) VALUES (3, 'Анджелина', 'Джоли');
INSERT INTO User (ID, FIRST_NAME, SECOND_NAME) VALUES (4, 'Тимур', 'Петров');
INSERT INTO User (ID, FIRST_NAME, SECOND_NAME) VALUES (5, 'Анастасия', 'Кисель');
INSERT INTO User (ID, FIRST_NAME, SECOND_NAME) VALUES (6, 'Петр', 'Волков');


INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('1', 'Сердечно-сосудистые препараты');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('2', 'Желудочно-кишечные препараты');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('3', 'Противоинфекционные препараты');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('4', 'Дерматологические препараты');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('5', 'Гормональные препараты');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('6', 'Урологические препараты');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('7', 'Витамины');
INSERT INTO Category (ID, CATEGORY_NAME) VALUES ('8', 'Косметика');

INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('1', 'en', 'Cardiovascular drugs');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('2', 'en', 'Gastrointestinal drugs');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('3', 'en', 'Antagonists drugs');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('4', 'en', 'Dermatological drugs');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('5', 'en', 'Hormonal drugss');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('6', 'en', 'Urological drugs');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('7', 'en', 'Vitamins');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('8', 'en', 'Cosmetics');

INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('1', 'ru', 'Сердечно-сосудистые препараты');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('2', 'ru', 'Желудочно-кишечные препараты');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('3', 'ru', 'Противоинфекционные препараты');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('4', 'ru', 'Дерматологические препараты');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('5', 'ru', 'Гормональные препараты');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('6', 'ru', 'Урологические препараты');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('7', 'ru', 'Витамины');
INSERT INTO Category_locale (ID_Category, LOCALE, CATEGORY_NAME) VALUES ('8', 'ru', 'Косметика');

update Category set CATEGORY_NAME_EN='Cardiovascular drugs' where id = '1';
update Category set CATEGORY_NAME_EN='Gastrointestinal drugs' where id = '2';
update Category set CATEGORY_NAME_EN='Antagonists drugs' where id = '3';
update Category set CATEGORY_NAME_EN='Dermatological drugs' where id = '4';
update Category set CATEGORY_NAME_EN='Hormonal drugs' where id = '5';
update Category set CATEGORY_NAME_EN='Urological drugs' where id = '6';
update Category set CATEGORY_NAME_EN='Vitamins' where id = '7';
update Category set CATEGORY_NAME_EN='Cosmetics' where id = '8';


INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('Цитрамон-Боримед', 'Ацетилсалициловая кислота-220мг, парацетамол-200мг, кофеин-27мг.', '6 таблеток','Инструкция', '1.00', '30', '0', 'css/img/citr.jpg', '1');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('Фурацилин', 'Нитрофурал, 20 мг, раствор для местного применения', '10 таблеток/20 мг', 'Инструкция', '0.5', '50', '0', 'css/img/fur.jpg', '3');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('Супрастин', 'Хлоропирамин, одна таблетка содержит 25 мг хлорпирамина гидрохлорида', '20 таблеток/25 мг', 'Инструкция', '4.08', '37', '0', 'css/img/supr.jpg', '4');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('Аугментин', 'Амоксициллин/клавулановая кислота. Таблетки, покрытые оболочокй.', '14 таблеток/875 мг', 'Инструкция', '12.35', '50', '1', 'css/img/augm.jpg', '3');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('Валериана-Белмед', '20 таблеток, покрытых оболочкой', '20 таблеток/200 мг', 'Инструкция', '1.77', '50', '0', 'css/img/valer.jpg', '1');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('Левомицетин', 'каждая таблетка содержит:
действующее вещество: хлорамфеникола (левомицетина) - 500 мг; вспомогательные вещества: повидон К-25, кальция стеарат, крахмал картофельный.', '10 таблеток/500 мг', 'Инструкция', '1.45', '50', '1', 'css/img/levo.jpg', '3');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('Берлиприл 5', '1 таблетка содержит:
 - Действующие вещества 
 - Эналаприла малеат 5 мг.
 - Вспомогательные вещества Желатин, лактозы моногидрат, магния стеарат, магния карбонат основной, кремния диоксид коллоидный, натрия карбоксиметилкрахмал.', '10 мг+10 мг ×28', 'Фармакологическое действие
 
 Берлиприл - ингибитор АПФ, антигипертензивный препарат.
 Эналаприл является пролекарством: в результате его гидролиза образуется эналаприлат, который ингибирует АПФ. Механизм действия препарата связан с уменьшением образования ангиотензина II из ангиотензина I, что приводит к уменьшению выделения альдостерона. При этом понижается ОПСС , систолическое и диастолическое АД, пост- и преднагрузка на миокард. Препарат уменьшает распад брадикинина, увеличивает синтез простагландина.
 Эналаприлат расширяет артерии в большей степени, чем вены, при этом рефлекторного повышения ЧСС не отмечается.
 Усиливает коронарный и почечный кровоток.', '22.54', '26', '0', 'css/img/beripril.jpg', '1');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('Валидол', 'действующее вещество: 1 таблетка содержит раствор ментола в ментиловом эфире кислоты изо- валериановой 0,06 г; 
 вспомогательные вещества: сахар измельченный (пудра), кальция стеарат.', '60 мг, 10 шт.','Фармакологическое действие
 
 Препарат проявляет успокаивающее дей¬ствие на центральную нервную систему, а также оказывает умеренное рефлекторное сосудорас¬ширяющее (коронаролитическое) влияние путем рефлекторного раздражения чувствительных нервных («холодовых») рецепторов слизистой оболочки полости рта. Стимуляция рецепторов сопровождается индукцией высвобождения эндорфинов, энкефалинов, динорфинов и других опиоидных пептидов, которые играют важную роль в уменьшении болевой чувствительности, нормализации проницаемости сосудов и регуляции других важных механизмов функционирова¬ния сердечно-сосудистой и нервной систем. Под влиянием препарата высвобождаются физиоло¬гически активные вещества - гистамин, кинины и др.', '1.02', '45', '0', 'css/img/validol.jpg', '1');
INSERT INTO Drug (DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION, PRICE, QUANTITY, NEED_RECIPE, IMAGE_PATH, FK_CATEGORY_ID) VALUES ('Бикалутамид Тева', 'Одна таблетка содержит: 
 активное вещество - бикалутамид 50 мг; 
 вспомогательные вещества - лактозы моногидрат, повидон К-30, натрия крахмала гликолят, магния стеарат. 
 Оболочка-. Опадрай белый Y-1-7000 (гипромеллоза, титана диоксид (Е 171), макрогол 400). ', 'таб 150мг №6','Фармакологическое действие
 
 Является рацемической смесью, обладающей нестероидной антиандрогенной активностью^ преимущественно за счет (11)-энантиомера. Другой эндокринной активностью не обладает. Связывается с андрогенными рецепторами без активации генной экспрессии и, таким образом, угнетает андрогенную стимуляцию. В результате происходит регрессия злокачественных новообразований предстательной железы. Бикалутамид обладает низкой степенью сродства с глобулином, связывающим половые гормоны, но это не имеет клинического значения. 
 Отмена лечения бикалутамидом может вызвать у некоторых пациентов синдром «отмены» антиандрогенов. ', '3.76', '25', '1', 'css/img/bika.jpg', '5');
 
 
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('1', 'ru', 'Цитрамон-Боримед', 'Ацетилсалициловая кислота-220мг, парацетамол-200мг, кофеин-27мг.', '6 таблеток','Инструкция');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('2', 'ru', 'Фурацилин', 'Нитрофурал, 20 мг, раствор для местного применения', '10 таблеток/20 мг', 'Инструкция');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('3', 'ru', 'Супрастин', 'Хлоропирамин, одна таблетка содержит 25 мг хлорпирамина гидрохлорида', '20 таблеток/25 мг', 'Инструкция');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('4', 'ru', 'Аугментин', 'Амоксициллин/клавулановая кислота. Таблетки, покрытые оболочокй.', '14 таблеток/875 мг', 'Инструкция');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('5', 'ru', 'Валериана-Белмед', '20 таблеток, покрытых оболочкой', '20 таблеток/200 мг', 'Инструкция');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('6', 'ru', 'Левомицетин', 'каждая таблетка содержит:
действующее вещество: хлорамфеникола (левомицетина) - 500 мг; вспомогательные вещества: повидон К-25, кальция стеарат, крахмал картофельный.', '10 таблеток/500 мг', 'Инструкция');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('7', 'ru', 'Берлиприл 5', '1 таблетка содержит:
 - Действующие вещества 
 - Эналаприла малеат 5 мг.
 - Вспомогательные вещества Желатин, лактозы моногидрат, магния стеарат, магния карбонат основной, кремния диоксид коллоидный, натрия карбоксиметилкрахмал.', '10 мг+10 мг ×28', 'Фармакологическое действие
 
 Берлиприл - ингибитор АПФ, антигипертензивный препарат.
 Эналаприл является пролекарством: в результате его гидролиза образуется эналаприлат, который ингибирует АПФ. Механизм действия препарата связан с уменьшением образования ангиотензина II из ангиотензина I, что приводит к уменьшению выделения альдостерона. При этом понижается ОПСС , систолическое и диастолическое АД, пост- и преднагрузка на миокард. Препарат уменьшает распад брадикинина, увеличивает синтез простагландина.
 Эналаприлат расширяет артерии в большей степени, чем вены, при этом рефлекторного повышения ЧСС не отмечается.
 Усиливает коронарный и почечный кровоток.');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('8', 'ru', 'Валидол', 'действующее вещество: 1 таблетка содержит раствор ментола в ментиловом эфире кислоты изо- валериановой 0,06 г; 
 вспомогательные вещества: сахар измельченный (пудра), кальция стеарат.', '60 мг, 10 шт.','Фармакологическое действие
 
 Препарат проявляет успокаивающее дей¬ствие на центральную нервную систему, а также оказывает умеренное рефлекторное сосудорас¬ширяющее (коронаролитическое) влияние путем рефлекторного раздражения чувствительных нервных («холодовых») рецепторов слизистой оболочки полости рта. Стимуляция рецепторов сопровождается индукцией высвобождения эндорфинов, энкефалинов, динорфинов и других опиоидных пептидов, которые играют важную роль в уменьшении болевой чувствительности, нормализации проницаемости сосудов и регуляции других важных механизмов функционирова¬ния сердечно-сосудистой и нервной систем. Под влиянием препарата высвобождаются физиоло¬гически активные вещества - гистамин, кинины и др.');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('9', 'ru', 'Бикалутамид Тева', 'Одна таблетка содержит: 
 активное вещество - бикалутамид 50 мг; 
 вспомогательные вещества - лактозы моногидрат, повидон К-30, натрия крахмала гликолят, магния стеарат. 
 Оболочка-. Опадрай белый Y-1-7000 (гипромеллоза, титана диоксид (Е 171), макрогол 400). ', 'таб 150мг №6','Фармакологическое действие
 
 Является рацемической смесью, обладающей нестероидной антиандрогенной активностью^ преимущественно за счет (11)-энантиомера. Другой эндокринной активностью не обладает. Связывается с андрогенными рецепторами без активации генной экспрессии и, таким образом, угнетает андрогенную стимуляцию. В результате происходит регрессия злокачественных новообразований предстательной железы. Бикалутамид обладает низкой степенью сродства с глобулином, связывающим половые гормоны, но это не имеет клинического значения. 
 Отмена лечения бикалутамидом может вызвать у некоторых пациентов синдром «отмены» антиандрогенов.');
 
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('1', 'en', 'Citramon-Borimed', 'Acetylsalicylic acid, 220mg, 200mg paracetamol, caffeine, 27mg.', '6 tablets','Instruction');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('2', 'en', 'Furatsilin', 'Nitrofurazone, 20 mg, topical solution', '10 tablets/20 mg', 'Instruction');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('3', 'en', 'Suprastin', 'Chloropyramine, one tablet contains 25 mg of hydrochloride chloropyramine', '20 tablets/25 mg', 'Instruction');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('4', 'en', 'Augmentin', 'Amoxicillin / clavulanic acid. Coated tablets.', '14 tablets/875 mg', 'Instruction');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('5', 'en', 'Valerian-Belmed', '20 coated tablets', '20 tablets/200 mg', 'Instruction');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('6', 'en', 'Сhloramphenicol', 'Each tablet contains:
active substance: chloramphenicol (chloramphenicol) - 500 mg; excipients: Povidone K-25, calcium stearate, potato starch.', '10 tablets/500 mg', 'Instruction');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('7', 'en', 'Berlipri 5', '1 tablet contains:
 - Active ingredients 
 - Enalapril maleate 5 mg.
 - Excipients: gelatin, lactose monohydrate, magnesium stearate, basic magnesium carbonate, colloidal silica, sodium carboxymethyl starch.', '10 mg+10 mg ×28', 'Pharmachologic effect
 
 Berlipril - an ACE inhibitor, an antihypertensive drug.
 Enalapril is a prodrug: formed by enalaprilat, which inhibits ACE as a result of hydrolysis. The mechanism of action of the drug is associated with a decrease in the formation of angiotensin II from angiotensin I, which reduces the release of aldosterone. This decreases total peripheral vascular resistance, systolic and diastolic blood pressure, post-and preload on the myocardium. The drug reduces the breakdown of bradykinin, increases the synthesis of prostaglandin.
 Enalaprilat artery expands to a greater extent than the vein, with a reflex increase in heart rate were observed.
 Enhances coronary and renal blood flow.');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('8', 'en', 'Validol', 'Active substance 1 tablet contains menthol menthyl ester dissolved in iso-valeric acid, 0.06 g;
  Other ingredients: shredded sugar (powder), calcium stearate.', '60 mg, 10 pieces.','Pharmachologic effect
 
 The drug exhibits dey¬stvie calming the central nervous system, and has a moderate reflex sosudoras¬shiryayuschee (koronarolitichesky) effect by reflex irritation
sensory nerve ( "cold") receptors oral mucosa. receptor stimulation is accompanied by the induction of the release of endorphins, enkephalins, and other opioid dynorphin
peptides that play an important role in the reduction of pain sensitivity, normalizing vascular permeability and the regulation of other important mechanisms funktsionirova¬niya cardiovascular and nervous
systems. Under the influence of the drug released fiziolo¬gicheski active substances - histamine, kinins, and others.');
INSERT INTO Drug_locale (ID_DRUG, LOCALE, DRUGNAME, DESCRIPTION, DOSAGE, INSTRUCTION) VALUES ('9', 'en', 'Bicalutamide Teva', 'One tablet contains:
  active ingredient - 50 mg bicalutamide;
  Excipients - lactose monohydrate, povidone K-30, sodium starch glycolate, magnesium stearate.
  A sheath. Opadry White Y-1-7000 (hypromellose, titanium dioxide (E 171), macrogol 400). ', 'tab 150mg №6','Pharmachologic effect
 
 It is a racemic mixture, having a non-steroidal antiandrogenic activity mainly due ^ (11) enantiomer. Other endocrine activity is not. It binds to androgenic receptors without activating gene expression and thus inhibits androgen stimulation. The result is a regression of prostate tumors. Bicalutamide has low affinity for binding globulin sex hormones, but this has no clinical significance.
 Cancel bicalutamide treatment may cause in some patients "cancel" syndrome antiandrogens.');