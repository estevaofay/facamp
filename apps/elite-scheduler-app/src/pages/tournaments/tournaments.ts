import { Component } from '@angular/core';
import { LoadingController, IonicPage, NavController, NavParams } from 'ionic-angular';
import { TeamsPage } from '../teams/teams';
import { EliteApi } from '../../shared/elite-api-service';
import { HubPage } from '../hub/hub';

/**
 * Generated class for the TournamentsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-tournaments',
  templateUrl: 'tournaments.html',
})
export class TournamentsPage {

  tournaments: any

  constructor(public navCtrl: NavController, 
              public navParams: NavParams, 
              public eliteApi : EliteApi, 
              public loadingController : LoadingController) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TournamentsPage');

    let loader = this.loadingController.create({content: "Getting data..."});
    loader.present().then(() => {
      this.eliteApi.getTournaments().then(data => {
        this.tournaments = data;
        console.log("Loaded: ", data);
        loader.dismiss();
      });
    });
    
  }

  tournamentTapped($event, tournament) {
    this.navCtrl.push(HubPage, tournament);  
  }

}
