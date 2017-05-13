/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.matching;

/**
 *
 * @author bhanuka
 */
public class BasicMatching extends MatchingUnit{
 
    @Override
    public void match(MatchingRequest request, MatchingResponse response) {
        response.BasicMatch = (float) (0.5 * this.subsequenceLength(request.getFileHash(), request.getMatchHash()) +
                0.3 * this.levenshteinLength(request.getFileHash(), request.getMatchHash()) +
                0.2 * this.substringLength(request.getFileHash(), request.getMatchHash()));
    }
    
    public float substringLength(String file, String match){
        
        int lcsLength = 0;
        float grade = 0;
        int mT_Length = match.length();
        int fT_Length = file.length();
        int[][] matrix = new int[mT_Length][fT_Length];

        for (int i = 0; i < mT_Length; i++) {
            for (int j = 0; j < fT_Length; j++) {
                if (match.charAt(i) == file.charAt(j)) {
                    if (i == 0 || j == 0) {
                        matrix[i][j] = 1;
                    }
                    else {
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    }
                    if (matrix[i][j] > lcsLength) {
                        lcsLength = matrix[i][j];
                    }
                }
            }
        }
        grade = ((float)lcsLength/(float)mT_Length);
        return grade;
    }
    
    public float subsequenceLength(String file, String match){
        int m = file.length();
	int n = match.length();
	int[][] dp = new int[m+1][n+1];
 
	for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(i==0 || j==0){
                        dp[i][j]=0;
                }else if(file.charAt(i-1)==match.charAt(j-1)){
                        dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
	}
 
	return (float)dp[m][n]/(float)Math.max(m,n);
    }
    
    public float levenshteinLength(String file, String match){
        int[][] distance;
        int cost = 0;
        float grade = 0;
        int vT_Length = match.length();
        int fT_Length = file.length();

        distance = new int[vT_Length + 1][fT_Length + 1];
        for (int i = 0; i <= vT_Length; i++){
            distance[i][0] = i;
        }
        for (int j = 0; j <= fT_Length; j++){
            distance[0][j] = j;
        }
        for (int i = 1; i <= vT_Length; i++){
            for (int j = 1; j <= fT_Length; j++) {
                if(match.charAt(i - 1) == file.charAt(j - 1)){
                    cost = 0;
                }
                else{
                    cost = 1;
                }
                distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1,
                                                   distance[i][j - 1] + 1)
                                          , distance[i - 1][j - 1] + cost);
            }
        }
        grade = distance[vT_Length][fT_Length];
        return 1 - (float)grade/(float)Math.max(vT_Length, fT_Length);
    }

}
