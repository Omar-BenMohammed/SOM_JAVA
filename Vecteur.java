	import java.io.BufferedInputStream;
    import java.io.BufferedReader;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.util.Random;

    public class Vecteur {
    	
    	double tab [][];
    	int nbe,tab3[],ligne=0,colone=0;
    	double tab2[],vectMoyen[],vectInf[],vectSup[];
    	double map[][][] =new double[6][10][4];
    	char fin[][];
    	final double alpha1=0.8;
    	final double alpha2=0.08;
    	
    	public Vecteur(String file) throws IOException {
    		
    	// lecture du fichier et initialisation de nbe (nombres de vecteurs dans le fichier)
    		InputStream is = new BufferedInputStream(new FileInputStream(file));
    		    try {
    		        byte[] c = new byte[1024];
    		        int count = 0;
    		        int readChars = 0;
    		        boolean empty = true;
    		        while ((readChars = is.read(c)) != -1) {
    		            empty = false;
    		            for (int i = 0; i < readChars; ++i) {
    		                if (c[i] == '\n') {
    		                    ++count;
    		                }
    		            } 
    		        }
    		        
    		        if (count == 0 && !empty)
    					this.nbe = 1;
    				else
    					this.nbe = count;
    		    } finally {
    		        is.close();
    		    }
    		    // initialisation de tab la matrice avec les vecteurs de base 
    		    tab=new double[this.nbe][4];
    		    // remplicage de la matrice avec les vecteurs de base
    			try {

    			    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    			    String line;
    			    // Uncomment the line below if you want to skip the fist line (e.g if headers)
    			    // line = br.readLine();
    			    int i=0;
    			    try {
    			    while ((line = br.readLine()) != null ) {
    			    	
    			    	final String SEPARATEUR = ",";
    			        String mots[] = line.split(SEPARATEUR);
    			 for (int j=0;j<4;j++) {
    				 tab [i][j] =Double.parseDouble(mots[j]);
    			 }
    			 i++;
    			    }}
    			    catch(ArrayIndexOutOfBoundsException b){
    			    
    			    br.close();}

    			} catch (IOException e) {
    			    System.out.println("ERROR: unable to read file " + file);
    			    e.printStackTrace();   
    			}
    			
    	    		
    	    	


    			// initialisation d'un vecteurs pour mettre les norme ce qui nous servira pour faire la matrice normaliser
    			this.tab2= new double[this.nbe];
    			for (int i = 0; i < tab.length; i++) {
    				double norme=0 ;

    				for(int j = 0; j< tab[i].length;j++) {
    				norme += Math.pow(tab[i][j],2);
    				}
    			 
    			tab2[i]=Math.sqrt(norme);
    			}
    			// creation de la matrice normalisee
    			for (int i = 0; i < tab.length; i++) {
    				for(int j = 0; j< tab[i].length;j++) {
    					tab[i][j]=(tab[i][j]/tab2[i]);
    				}
    			}
    			// creation du vecteur melange qui contien les chiffres de 0 a 150 totalement melange
    			int min=0,max=149;
    			this.tab3=new int[this.nbe];
    			for(int i =0;i<tab3.length;i++) {
    				tab3[i]=i;
    			}
    			for(int i=0;i<tab3.length;i++) {
    			Random rand = new Random();
    				int nombreAleatoire = rand.nextInt(max - min + 1) + min;
    				tab3[i]=nombreAleatoire;
    				tab3[nombreAleatoire]=i;
    				
    			}
    			// cretion des vectuers moyens sup inf qui nous servira a fair les vecteurs W
    			this.vectMoyen = new double[tab[0].length];
    			this.vectInf = new double[tab[0].length];
    			this.vectSup = new double[tab[0].length];
    			for (int i = 0; i < tab[i].length; i++) {
    				for(int j = 0; j< tab.length;j++) {
    					vectMoyen[i]+=tab[j][i];
    					
    				}
    				}
    			for(int i=0;i<vectMoyen.length;i++) {
    				vectMoyen[i]=(vectMoyen[i]/(double)(this.nbe));	
    				vectInf[i]=vectMoyen[i]-0.05;
    				vectSup[i]=vectMoyen[i]+0.05;
    				}
    			
    			this.fin=new char [6][10]; 
    		}
    	    // procedure pour afficher une matice de nombres doubles		
    		void afficher(double tab[][]) {
    		System.out.println("La Matrice toute entiere");
    		System.out.println();
    		for(int i=0; i<this.nbe;i++) {
    			for(int j=0; j<4;j++) {
    				System.out.printf("%s%.4f%s","  ",tab[i][j], "  ");
    			}
    			System.out.println();
    		}
    	}
    	    // procedure pour afficher une matice de caracteres		

    		void afficher(char tab[][]) {
    		System.out.println("La Matrice toute entiere");
    		System.out.println();
    		for(int i=0; i<tab.length;i++) {
    			for(int j=0; j<tab[i].length;j++) {
    				System.out.printf("%s%c%s"," ",tab[i][j], " ");
    			}
    			System.out.println();

    			System.out.println();
    		}
    	}
    		//creation de la map de vecteurs W 
    		void creer(double tab[],double tab2[],double map[][][]) {


    			for(int i =0;i<map.length;i++) {
    				for(int j =0;j<map[i].length;j++) {
    					for(int k =0;k<map[i][j].length;k++) {
    						Random rand = new Random();
    						double randomValue = tab[k] + (tab2[k] - tab[k]) * rand.nextDouble();
    						map[i][j][k]=randomValue;
    						//System.out.println(randomValue);
    					}
    				}
    			}
    		}
    		// procedure pour afficher la map de vecteurs W
    		void afficher(double map[][][]) {
    			for(int i =0;i<map.length;i++) {
    				for(int j =0;j<map[i].length;j++) {
    					for(int k =0;k<map[i][j].length;k++) {
    						System.out.printf("%s%f%s","  ",map[i][j][k], "  ");
    					}}
    				System.out.println();
    				}
    		}
    		// fonction qui retourne la distance euclidienne entere deux vecteurs a laquelle on fera appel dans bmu
    		double distance(double tab[],double tab2[]){
    			double dist=0.0;
    			for(int i=0;i<tab.length;i++) {
    				dist+=Math.pow((tab[i]-tab2[i]),2);
    			}
    			dist=Math.sqrt(dist);
    			return dist;
    					}
    		// procedure qui trouve le bmu puis modifie le voisinage donne
    		void bmu(int tab[],double norme[][],double mat[][][],int voisinage,double alpha) {
    			int linmax=0,colmax=0,linmin=0,colmin=0;

    			for(int i =0;i<norme.length;i++) {
    			double dist=distance(norme[tab[i]],mat[0][0]);
    			for(int j =0;j<mat.length;j++) {
    				for(int k =0;k<mat[j].length;k++) {
    					if (distance(norme[tab[i]],mat[j][k])<dist) {
    						dist=distance(norme[tab[i]],mat[j][k]);
    						ligne=j;
    						colone=k;
    						
    					}
    				}

    				
    			}
    			linmin=ligne-voisinage;
    			linmax=ligne+voisinage+1;
    			colmin=colone-voisinage;
    			colmax=colone+voisinage+1;
    			if(linmin<0) {
    				linmin=0;
    			}
    			
    			if(linmax>(mat.length)) {
    				linmax=mat.length;
    			}
    			
    			if(colmin<0) {
    				colmin=0;
    			}
    			
    			if(colmax>(mat[ligne].length)) {
    				colmax=mat[ligne].length;
    			}

    				for(int j=linmin;j<linmax;j++) {
    					for(int k=colmin;k<colmax;k++) {
    						for(int l=0;l<mat[j][k].length;l++) {
    							
    							mat[j][k][l]=mat[j][k][l]+(alpha*(norme[tab[i]][l]-mat[j][k][l]));
    							}
    					}
    				}
    	

    			}
    			}
    		// procedure qui lance la premiere phase d'itterations
    			
    		void itter(int tab[],double norme[][],double mat[][][],int voisinage) {
    			double alp=alpha1;
    			int voi=voisinage;
    			bmu(tab,norme,mat,voisinage,alpha1);
    			for(int i=1;i<300;i++) {
    					alp=alpha1*(1-(double)(i+1)/300);
    				if(i<100) {
    					bmu(tab,norme,mat,voisinage,alp);

    				}else if((i>=100) && (i<200)){
    					  voi-=1;
    						bmu(tab,norme,mat,voi,alp);

    				}else {
    					 voi-=2;
    						bmu(tab,norme,mat,voi,alp);

    				}
    			}}
    		// procedure qui lance la 2eme phase d'itterations

    			void itter2(int tab[],double norme[][],double mat[][][],int voisinage) {
    				double alp=alpha2;
    				bmu(tab,norme,mat,voisinage,alpha2);
    			for(int i=1;i<1700;i++) {
    					alp=alpha2*(1-((double)(i+1)/1700));
    				bmu(tab,norme,mat,voisinage,alp);

    				}
    			}
    			
    		// procedure qui trouve le bmu final et qui rempli la matrice avec les caracteres qu'il faut
    		void bmuFinal(double norme[][],double mat[][][],char fin[][]) {
    			char c;
    			for(int i=0;i<fin.length;i++) {
    				for(int j=0;j<fin[i].length;j++) {
    					fin[i][j]='*';
    				}
    			}
    			
    			for(int i =0;i<norme.length;i++) {
    				double dist=distance(norme[i],mat[0][0]);
    				for(int j =0;j<mat.length;j++) {
    					for(int k =0;k<mat[j].length;k++) {
    						if (distance(norme[i],mat[j][k])<dist) {
    							dist=distance(norme[i],mat[j][k]);
    							ligne=j;
    							colone=k;
    							
    						}
    					}
    				}
    				if(i<50) {
    					c='A';
    				}else if(i>=50 && i<100) {
    					c='B';
    				}else {
    				c='C';
    				}
    				fin[ligne][colone]= c;
    		}

    		}
    		
    		public static void main(String args[]) throws IOException {
    			// le main est deja pret juste mettre le bon chemain du fichier
    			String file ="iris data.txt";
    			Vecteur v=new Vecteur(file);
    			v.creer(v.vectInf,v.vectSup,v.map);
    			v.itter(v.tab3,v.tab,v.map,3);
    			v.itter2(v.tab3,v.tab,v.map,1);
    			v.bmuFinal(v.tab, v.map, v.fin);
    			v.afficher(v.fin);
    			
    }

    }
    		
    		
