package com.jo.switchgate.core.navigation.di

import com.jo.switchgate.core.navigation.Feature
import com.jo.switchgate.core.navigation.features.account.AccountEntry
import com.jo.switchgate.core.navigation.features.account.LanguageEntry
import com.jo.switchgate.core.navigation.features.account.PersonalDataEntry
import com.jo.switchgate.core.navigation.features.account.PickImageEntry
import com.jo.switchgate.core.navigation.features.authentication.OTPEntry
import com.jo.switchgate.core.navigation.features.authentication.SignUpEntry
import com.jo.switchgate.core.navigation.features.cities.CitiesEntry
import com.jo.switchgate.core.navigation.features.complex.ComplexEntry
import com.jo.switchgate.core.navigation.features.complexlist.ComplexListEntry
import com.jo.switchgate.core.navigation.features.countries.CountriesEntry
import com.jo.switchgate.core.navigation.features.discovermatches.DiscoverMatchesEntry
import com.jo.switchgate.core.navigation.features.discovermatches.DiscoverMatchesFilterEntry
import com.jo.switchgate.core.navigation.features.evaluationform.EvaluationFormEntry
import com.jo.switchgate.core.navigation.features.home.HomeEntry
import com.jo.switchgate.core.navigation.features.matches.MatchDetailsEntry
import com.jo.switchgate.core.navigation.features.matches.MatchesEntry
import com.jo.switchgate.core.navigation.features.matches.SelectTeamEntry
import com.jo.switchgate.core.navigation.features.matchlist.MatchListEntry
import com.jo.switchgate.core.navigation.features.notifications.NotificationsEntry
import com.jo.switchgate.core.navigation.features.payment.AddCardEntry
import com.jo.switchgate.core.navigation.features.payment.PaymentEntry
import com.jo.switchgate.core.navigation.features.payment.SuccessPaymentEntry
import com.jo.switchgate.core.navigation.features.tournaments.InvitePlayerEntry
import com.jo.switchgate.core.navigation.features.tournaments.SearchEntry
import com.jo.switchgate.core.navigation.features.tournaments.TournamentFormEntry
import com.jo.switchgate.core.navigation.features.tournaments.TournamentPersonalDataEntry
import com.jo.switchgate.core.navigation.features.tournaments.TournamentsEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @IntoMap
    @ClassKey(AccountEntry::class)
    fun bindsAccountEntry(accountEntry: AccountEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(HomeEntry::class)
    fun bindsHomeEntry(homeEntry: HomeEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(MatchesEntry::class)
    fun bindsMatchesEntry(homeEntry: MatchesEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(NotificationsEntry::class)
    fun bindsNotificationsEntry(notificationEntry: NotificationsEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(SignUpEntry::class)
    fun bindsSignUpEntry(signUpEntry: SignUpEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(OTPEntry::class)
    fun bindsOTPEntry(otpEntry: OTPEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(CitiesEntry::class)
    fun bindsCitiesEntry(citiesEntry: CitiesEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(CountriesEntry::class)
    fun bindsCountriesEntry(countriesEntry: CountriesEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(DiscoverMatchesEntry::class)
    fun bindsDiscoverMatchesEntry(discoverMatchesEntry: DiscoverMatchesEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(DiscoverMatchesFilterEntry::class)
    fun bindsDiscoverMatchesFilterEntry(discoverMatchesFilterEntry: DiscoverMatchesFilterEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(ComplexListEntry::class)
    fun bindsComplexListEntry(complexListEntry: ComplexListEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(MatchListEntry::class)
    fun bindsMatchListEntry(matchListEntry: MatchListEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(ComplexEntry::class)
    fun bindsComplexEntry(complexEntry: ComplexEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(MatchDetailsEntry::class)
    fun bindsMatchDetailsEntry(matchDetailsEntry: MatchDetailsEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(PersonalDataEntry::class)
    fun bindsPersonalDataEntry(personalDataEntry: PersonalDataEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(PickImageEntry::class)
    fun bindsPickImageEntry(pickImageEntry: PickImageEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(LanguageEntry::class)
    fun bindsLanguageEntry(languageEntry: LanguageEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(TournamentsEntry::class)
    fun bindsTournamentsEntry(tournamentsEntry: TournamentsEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(TournamentPersonalDataEntry::class)
    fun bindTournamentPersonalDataEntry(tournamentPersonalDataEntry: TournamentPersonalDataEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(TournamentFormEntry::class)
    fun bindTournamentFormEntry(tournamentFormEntry: TournamentFormEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(SearchEntry::class)
    fun bindSearchEntry(searchEntry: SearchEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(InvitePlayerEntry::class)
    fun bindInvitePlayerEntry(invitePlayerEntry: InvitePlayerEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(PaymentEntry::class)
    fun bindPaymentEntry(paymentEntry: PaymentEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(SuccessPaymentEntry::class)
    fun bindSuccessPaymentEntry(successPaymentEntry: SuccessPaymentEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(EvaluationFormEntry::class)
    fun bindEvaluationFormEntry(evaluationFormEntry: EvaluationFormEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(SelectTeamEntry::class)
    fun bindSelectTeamEntry(selectTeamEntry: SelectTeamEntry): Feature

    @Binds
    @IntoMap
    @ClassKey(AddCardEntry::class)
    fun bindAddCardEntry(addCardEntry: AddCardEntry): Feature
}
