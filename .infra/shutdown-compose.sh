rm -rf volumes
docker-compose -f common.yml -f docker-compose.yml down

cnt_coupon_network=`docker network ls --filter name=coupon-network | wc -l`
cnt_coupon_network=$(($cnt_coupon_network -1))

if [ $cnt_coupon_network -ne 0 ]
then
  echo "'coupon-network' 가 존재합니다. 'coupon-network' 를 삭제합니다."
  docker network rm coupon-network
fi