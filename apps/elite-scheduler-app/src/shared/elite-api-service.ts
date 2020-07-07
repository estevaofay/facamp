import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class EliteApi {
    private baseUrl = 'https://elite-schedule-app-8c99a.firebaseio.com';
    constructor(private http: Http){

    }

    getTournaments(){
        return new Promise(resolve => {
            this.http.get(`${this.baseUrl}/tournaments.json`).subscribe(
                data => {
                    resolve(data.json())
                }
            )


        });
    }

    getTeams(tournamentId : String){
        return new Promise(resolve => {
            this.http.get(`${this.baseUrl}/tournaments-data/${tournamentId}/teams.json`).subscribe(
                data => {
                    resolve(data.json())
                }
            )


        });
    }

    getStandings(tournamentId: String){
        return new Promise(resolve => {
            this.http.get(`${this.baseUrl}/tournaments-data/${tournamentId}/standings.json`).subscribe(
                data => {
                    resolve(data.json())
                }
            )


        });
    }

    getGames(tournamentId: String){
        return new Promise(resolve => {
            this.http.get(`${this.baseUrl}/tournaments-data/${tournamentId}/games.json`).subscribe(
                data => {
                    resolve(data.json())
                }
            )


        });
    }

}