package Morse_parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Morse{
	
	private static final String[] signal={"�E�|","�E�|�E�|","�|�E�E�E","�|�E�|�E","�|�E�E","�E","�E�E�|�E�E","�E�E�|�E","�|�|�E","�E�E�E�E","�|�E�|�|�E","�E�|�|�|","�|�E�|","�E�|�E�E","�|�|","�|�E","�|�|�|","�|�|�|�E","�E�|�|�E","�|�|�E�|","�E�|�E","�E�E�E","�|","�E�E�|","�E�|�E�E�|","�E�E�|�|","�E�|�E�E�E","�E�E�E�|","�E�|�|","�|�E�E�|","�|�E�|�|","�|�|�E�E","�|�|�|�|","�|�E�|�|�|","�E�|�E�|�|","�|�|�E�|�|","�|�E�|�E�|","�|�E�|�E�E","�|�E�E�|�|","�|�E�E�E�|","�E�E�|�E�|","�|�|�E�|�E","�E�|�|�E�E","�|�|�E�E�|","�|�E�E�|�E","�E�|�|�|�E","�|�|�|�E�|","�E�|�E�|�E","�E�E","�E�E�|�|�E","�E�|�|�|�|","�E�E�|�|�|","�E�E�E�|�|","�E�E�E�E�|","�E�E�E�E�E","�|�E�E�E�E","�|�|�E�E�E","�|�|�|�E�E","�|�|�|�|�E","�|�|�|�|�|","�E�|�|�E�|","�E�|�E�|�E�|","�E�|�E�|�E�E","�|�E�|�|�E�|","�E�|�E�E�|�E"};
	private static final String[] compil_kana={"�C","��","�n","�j","�z","�w","�g","�`","��","�k","��","��","��","�J","��","�^","��","�\","�c","�l","�i","��","��","�E","��","�m","�I","�N","��","�}","�P","�t","�R","�G","�e","�A","�T","�L","��","��","�~","�V","��","�q","��","�Z","�X","��","�J","�K","1","2","3","4","5","6","7","8","9","0","�[","�A","\n","�i"," �j"};
	private static final String[] compil_hira={"��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","�J","�K","1","2","3","4","5","6","7","8","9","0","�[","�A","\n","�i"," �j"};
	private static final String[] daku_kana={"�K","�M","�O","�Q","�S","�U","�W","�Y","�[","�]","�_","�a","�d","�f","�h","�o","�r","�u","�x","�{","�p","�s","�v","�y","�|"};
	private static final String[] daku_hira={"��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��"};
	private static final String[] daku_nasi={"�J�J","�L�J","�N�J","�P�J","�R�J","�T�J","�V�J","�X�J","�Z�J","�\�J","���J","�`�J","�c�J","�e�J","�g�J","�n�J","�q�J","�t�J","�w�J","�z�J","�n�K","�q�K","�t�K","�w�K","�z�K"};
	
	
	//
	//compil_kana��compil_hira�Ɋ܂܂�镶�������[���X�ɕϊ�
	//���[���X�ɂł��Ȃ������͂��̂܂܁B
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
	//���[���X���ۂ��������J�^�J�i�ɕϊ�����B
	//�ϊ���̓��[���X������������("���[���X")�ƂȂ�
	//���̑��͂��̂܂�
	//
	public static String parser(String text){
		
		Pattern pattern=Pattern.compile("(((�||�E)+) ?)+", Pattern.CASE_INSENSITIVE);
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
				list.add("(\""+ress+"\")");//�����ς����("")�ȊO�ɂ��ł����
			}
		}
		
		for(String s:list)text=text.replaceFirst("(((�||�E)+) ?)+",s);

		return text;
	}

}