import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { EliteApi } from '../../shared/elite-api-service';

/**
 * Generated class for the HubPage tabs.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-hub',
  templateUrl: 'hub.html'
})
export class HubPage {

  gamesRoot = 'GamesPage'
  standingsRoot = 'StandingsPage'
  teamsRoot = 'TeamsPage'

  tournament: any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public eliteApi : EliteApi, 
              public loadingController : LoadingController) {
                  this.tournament = navParams.data; 
              }
}
