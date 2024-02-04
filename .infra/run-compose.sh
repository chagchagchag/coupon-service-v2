cnt_coupon_network=`docker network ls --filter name=coupon-network | wc -l`
cnt_coupon_network=$(($cnt_coupon_network -1))

if [ $cnt_coupon_network -eq 0 ]
then
  echo "'coupon-network' 가 존재하지 않습니다. 'coupon-network'를 새로 생성합니다."
  docker network create coupon-network
fi

docker-compose -f common.yml -f docker-compose.yml up -d