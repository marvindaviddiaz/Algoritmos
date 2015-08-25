package snake;

public class Main {
	
	String[][] matriz = new String[][] { 
	{"1", 	"2", 	"3", 	"4",	"5",	"6", 	"7", 	"8", 	"9",	"10"},
	{"34", 	"35", 	"36", 	"37",	"38",	"39", 	"40", 	"41", 	"42",	"11"},
	{"33", 	"60", 	"61", 	"62",	"63",	"64", 	"65", 	"66", 	"43",	"12"},
	{"32", 	"59", 	"78", 	"79",	"80",	"81",	"82", 	"67", 	"44",	"13"},
	{"31", 	"58", 	"77", 	"88",	"89",	"90", 	"83", 	"68", 	"45",	"14"},
	{"30", 	"57",	"76", 	"87",	"86",	"85", 	"84", 	"69", 	"46",	"15"},
	{"29", 	"56", 	"75", 	"74",	"73",	"72", 	"71", 	"70", 	"47",	"16"},
	{"28", 	"55", 	"54", 	"53",	"52",	"51", 	"50", 	"49", 	"48",	"17"},
	{"27", 	"26", 	"25", 	"24",	"23",	"22", 	"21", 	"20", 	"19",	"18"}};

	int limX = matriz[0].length;
	int limY = matriz.length;
	int iniX = -1;
	int iniY = -1;
	int[][] ma = new int[limY][limX];
	int x = 0, y = 0;
	int direccion = 0;
	int errors = 0;
	
	public void recorrer() {
		if (ma[y][x] == 0) {
			System.out.print(matriz[y][x] + " ");
			ma[y][x] = 1;
			errors = 0;
		}else{
			errors ++;
			if (errors > 3) {
				System.exit(0);
			}
		}
		
		switch (direccion) {
		case 0: // DERECHA
			x++;
			if (x == limX) {
				x--;
				direccion++;
			}
			break;	
		case 1: // ABAJO
			y++;
			if (y == limY) {
				y--;
				direccion++;

			}
			break;			
		case 2: // IZQUIERDA
			x--;
			if (x == iniX) {
				x++;
				direccion++;
			}
			break;			
		case 3: // ARRIBA
			y--;
			if (y == iniY) {
				y++;
				direccion++;
			}
			break;
		default:
			break;
		}
		
		if (direccion == 4) {
			direccion = 0;
			limX--;
			limY--;
			iniX++;
			iniY++;
			y++;
		}
		recorrer();
	}
	
	public static void main(String[] args) {
		new Main().recorrer();
	}
}