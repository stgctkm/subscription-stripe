# subscription-stripe
subscription sample

## 事前準備
### サービス、料金の登録

#### dashboard、もしくはcliで登録

https://stripe.com/docs/billing/subscriptions/fixed-price#create-business-model

```
cli/stripe products create \
  --name="稽古" \
  --description="稽古のサービス"

```

```
{
  "id": "prod_H94k5odtwJXMtQ",
```

- priceの登録 
  - application.properties に設定して、subscribe 時に使用する
```
stripe prices create \
  -d product=prod_H94k5odtwJXMtQ \
  -d unit_amount=1500 \
  -d currency=jpy \
  -d "recurring[interval]"=month
```

## 実行環境の設定

### 環境変数
dashboard から取得する

|  key  |  value  |
| ---- | ---- |
|  STRIPE_API_SECRET_KEY  |  xxx  |
|  STRIPE_WEBHOOK_SECRET  |  xxx  |

## screen shot

- start
![index](/doc/images/1_Index.png)

- Customer 登録
![customer](/doc/images/2_register_customer.png)

- クレジットカード登録
![Save a card](/doc/images/3_Saving_a_Card.png)

- クレジットカード登録結果
![complete](/doc/images/4_Saving_a_Card_Complete.png)

- Subscribe
![Subscibe](/doc/images/5_Subscription.png)

- 結果
![Result](/doc/images/6_Result.png)

