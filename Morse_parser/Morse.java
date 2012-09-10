package Morse_parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Morse{
	
	private static final String[] signal={"・－","・－・－","－・・・","－・－・","－・・","・","・・－・・","・・－・","－－・","・・・・","－・－－・","・－－－","－・－","・－・・","－－","－・","－－－","－－－・","・－－・","－－・－","・－・","・・・","－","・・－","・－・・－","・・－－","・－・・・","・・・－","・－－","－・・－","－・－－","－－・・","－－－－","－・－－－","・－・－－","－－・－－","－・－・－","－・－・・","－・・－－","－・・・－","・・－・－","－－・－・","・－－・・","－－・・－","－・・－・","・－－－・","－－－・－","・－・－・","・・","・・－－・","・－－－－","・・－－－","・・・－－","・・・・－","・・・・・","－・・・・","－－・・・","－－－・・","－－－－・","－－－－－","・－－・－","・－・－・－","・－・－・・","－・－－・－","・－・・－・"};
	private static final String[] compil_kana={"イ","ロ","ハ","ニ","ホ","ヘ","ト","チ","リ","ヌ","ル","ヲ","ワ","カ","ヨ","タ","レ","ソ","ツ","ネ","ナ","ラ","ム","ウ","ヰ","ノ","オ","ク","ヤ","マ","ケ","フ","コ","エ","テ","ア","サ","キ","ユ","メ","ミ","シ","ヱ","ヒ","モ","セ","ス","ン","゛","゜","1","2","3","4","5","6","7","8","9","0","ー","、","\n","（"," ）"};
	private static final String[] compil_hira={"い","ろ","は","に","ほ","へ","と","ち","り","ぬ","る","を","わ","か","よ","た","れ","そ","つ","ね","な","ら","む","う","ヰ","の","お","く","や","ま","け","ふ","こ","え","て","あ","さ","き","ゆ","め","み","し","ヱ","ひ","も","せ","す","ん","゛","゜","1","2","3","4","5","6","7","8","9","0","ー","、","\n","（"," ）"};
	private static final String[] daku_kana={"ガ","ギ","グ","ゲ","ゴ","ザ","ジ","ズ","ゼ","ゾ","ダ","ヂ","ヅ","デ","ド","バ","ビ","ブ","ベ","ボ","パ","ピ","プ","ペ","ポ"};
	private static final String[] daku_hira={"が","ぎ","ぐ","げ","ご","ざ","じ","ず","ぜ","ぞ","だ","ぢ","づ","で","ど","ば","び","ぶ","べ","ぼ","ぱ","ぴ","ぷ","ぺ","ぽ"};
	private static final String[] daku_nasi={"カ゛","キ゛","ク゛","ケ゛","コ゛","サ゛","シ゛","ス゛","セ゛","ソ゛","た゛","チ゛","ツ゛","テ゛","ト゛","ハ゛","ヒ゛","フ゛","ヘ゛","ホ゛","ハ゜","ヒ゜","フ゜","ヘ゜","ホ゜"};
	
	
	//
	//compil_kanaとcompil_hiraに含まれる文字をモールスに変換
	//モールスにできない部分はそのまま。
	//
	
	public static String create(String text){
		
		for(int i=0;i<daku_kana.length;i++){
			text=text.replaceAll(daku_kana[i], daku_nasi[i]);
			text=text.replaceAll(daku_hira[i], daku_nasi[i]);
		}
		
		
		for(int i=0;i<signal.length;i++){
			text=text.replace(compil_kana[i], signal[i]+" ");
			text=text.replace(compil_hira[i], signal[i]+" ");
		}
		return text;
	}
	
	//
	//モールスっぽい部分をカタカナに変換する。
	//変換後はモールスだった部分が("モールス")となる
	//その他はそのまま
	//
	public static String parser(String text){
		
		Pattern pattern=Pattern.compile("(((－|・)+) ?)+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		
		ArrayList<String> list=new ArrayList<String>();
		String ress;
		while(matcher.find()){
			String m=matcher.group();
			String[] s_m = m.split(" ");
			if(s_m.length>1){
				ress="";
				for(int j=0;j<s_m.length;j++){
					for(int i=0;i<signal.length;i++){
						if(s_m[j].equals(signal[i])){
							ress+=compil_kana[i];
							break;
						}
					}
				}
				list.add("(\""+ress+"\")");//ここ変えれば("")以外にもできるよ
			}
		}
		
		for(String s:list)text=text.replaceFirst("(((－|・)+) ?)+",s);

		return text;
	}

}