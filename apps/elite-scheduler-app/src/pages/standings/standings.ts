import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { EliteApi } from '../../shared/elite-api-service';

/**
 * Generated class for the StandingsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-standings',
  templateUrl: 'standings.html',
})
export class StandingsPage {
  tournament: any;
  standings: any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public eliteApi : EliteApi, 
              public loadingController : LoadingController) {
              this.tournament = navParams.data; 
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad StandingsPage');
    this.tournament.id;

    let loader = this.loadingController.create({content: "Getting Standings Data..."});
    loader.present().then(() => {
      this.eliteApi.getStandings(this.tournament.id).then(data => {
        this.standings = data;
        console.log("Loaded: ", data);
        loader.dismiss();
      });
    });
  }

  standingTapped($event, standing, team, tournament){
    
  }

}
