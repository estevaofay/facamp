import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { EliteApi } from '../../shared/elite-api-service';

/**
 * Generated class for the TeamDetailPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-team-detail',
  templateUrl: 'team-detail.html',
})
export class TeamDetailPage {

  tournament: any;
  team: any;
  teamDetail: any;
  index = 0;

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              public eliteApi : EliteApi, 
              public loadingController : LoadingController) {
              this.team = navParams.data;      
              
  
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TeamsPage');    
  }

}
