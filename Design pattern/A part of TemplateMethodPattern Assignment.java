import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진  
 * @file BlackJackPlayerHand.java
 * 탬플릿 메소드 패턴
 * 블랙잭 게임에서 각 플레이어의 패 정보 유지
 */
public class BlackJackPlayerHand {
	private List<Card> cards;
	private int score = 0;
	private boolean isBlackJack = false;

	private int aceNum=0;
	private int jkq=0;


	public BlackJackPlayerHand(List<Card> cards) {
		this.cards = cards;
		score = computeScore();
	}
	public void init() {
		cards.clear();
		score = 0;
		isBlackJack = false;
	}
	public List<Card> getCards(){
		return cards;
	}
	public void addCard(Card card) {
		cards.add(card);
		score = computeScore();
	}
	public int getScore() {
		return score;
	}
	public boolean isBlackJack() {
		return isBlackJack;
	}
	private int computeScore() {
		aceNum=0;
		jkq=0;
		score=0;
		int length=cards.size();
		for(int i=0;i<length;i++){
			int number=cards.get(i).number();
			if(number==1)aceNum+=1;
			//카드는 계속 뽑을 수 있어야 하므로 Ace는 그대로 1로 처리.
			else if(number==11||number==12||number==13){
				jkq+=1;
				number=10;
			}
			score+=number;
		}
		if(score==11 && aceNum==1 && jkq==1)isBlackJack=true;
		else isBlackJack=false;
		return score;
	}
	public static BlackJackGameResult determineResult(
			BlackJackPlayerHand userHand, BlackJackPlayerHand dealerHand) {
		if(userHand.isBlackJack && dealerHand.isBlackJack)
			return BlackJackGameResult.DRAW;
		else if(userHand.isBlackJack)
			return BlackJackGameResult.USERWIN;
		else if(dealerHand.isBlackJack)
			return BlackJackGameResult.USERLOST;
		int userScore=userHand.getScore();
		int userAceNum=userHand.aceNum;
		while(userAceNum>0 && userScore+10<=21) {
			userScore += 10;
			userAceNum-=1;
		}
		userHand.score=userScore;
		userHand.aceNum=userAceNum;
		int dealerScore=dealerHand.getScore();
		int dealerAceNum=dealerHand.aceNum;
		while(dealerAceNum>0 && dealerScore+10<=21){
			dealerScore+=10;
			dealerAceNum-=1;
		}
		dealerHand.score=dealerScore;
		dealerHand.aceNum=dealerAceNum;
		if(userScore>21 && dealerScore>21)
			return BlackJackGameResult.DRAW;
		else if(userScore>21)
			return BlackJackGameResult.USERLOST;
		else if(dealerScore>21)
			return BlackJackGameResult.USERWIN;
		else
			return userScore>dealerScore?BlackJackGameResult.USERWIN : BlackJackGameResult.USERLOST;


	}
}
