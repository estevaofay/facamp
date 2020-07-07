import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { EliteApi } from '../../shared/elite-api-service';

/**
 * Generated class for the GamesPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-games',
  templateUrl: 'games.html',
})
export class GamesPage {
  tournament: any;
  games: any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public eliteApi : EliteApi, 
              public loadingController : LoadingController) {
                this.tournament = navParams.data; 
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad GamesPage');
    this.tournament.id;

    let loader = this.loadingController.create({content: "Getting Games Data..."});
    loader.present().then(() => {
      this.eliteApi.getGames(this.tournament.id).then(data => {
        this.games = data;
        console.log("Loaded: ", data);
        loader.dismiss();
      });
    });
  }
  
  gameTapped($event, game, team, tournament){

  }

}
