package io.kx.loanapp.view;

import com.google.protobuf.Any;
import io.kx.loanapp.api.LoanAppApi;
import io.kx.loanapp.domain.LoanAppDomain;
import kalix.javasdk.view.View;
import kalix.javasdk.view.ViewContext;

// This class was initially generated based on the .proto definition by Kalix tooling.
// This is the implementation for the View Service described in your io/kx/loanapp/view/loan_app_view.proto file.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class LoanAppByStatusView extends AbstractLoanAppByStatusView {

  public LoanAppByStatusView(ViewContext context) {}

  @Override
  public LoanAppViewByStatusModel.LoanAppViewState emptyState() {
    return LoanAppViewByStatusModel.LoanAppViewState.getDefaultInstance();
  }

  @Override
  public View.UpdateEffect<LoanAppViewByStatusModel.LoanAppViewState> onSubmitted(
      LoanAppViewByStatusModel.LoanAppViewState state,
      LoanAppDomain.Submitted submitted) {

    LoanAppViewByStatusModel.LoanAppViewState viewState = LoanAppViewByStatusModel.LoanAppViewState.newBuilder()
            .setLoanAppId(submitted.getLoanAppId())
            .setStatus(LoanAppApi.LoanAppStatus.STATUS_IN_REVIEW)
            .setStatusId(LoanAppApi.LoanAppStatus.STATUS_IN_REVIEW.getNumber())
            .setLastUpdatedTimestamp(submitted.getEventTimestamp())
            .build();

    return effects().updateState(viewState);
  }

  @Override
  public View.UpdateEffect<LoanAppViewByStatusModel.LoanAppViewState> onApproved(
      LoanAppViewByStatusModel.LoanAppViewState state,
      LoanAppDomain.Approved approved) {

    LoanAppViewByStatusModel.LoanAppViewState viewState = LoanAppViewByStatusModel.LoanAppViewState.newBuilder()
            .setStatus(LoanAppApi.LoanAppStatus.STATUS_APPROVED)
            .setStatusId(LoanAppApi.LoanAppStatus.STATUS_APPROVED.getNumber())
            .setLastUpdatedTimestamp(approved.getEventTimestamp())
            .build();

    return effects().updateState(viewState);
  }

  @Override
  public View.UpdateEffect<LoanAppViewByStatusModel.LoanAppViewState> ignoreAnyOtherEvent(
      LoanAppViewByStatusModel.LoanAppViewState state,
      Any any) {
    return effects().ignore();
  }

}

